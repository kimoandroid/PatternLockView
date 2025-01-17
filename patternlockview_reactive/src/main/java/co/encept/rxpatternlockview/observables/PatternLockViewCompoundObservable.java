package co.encept.rxpatternlockview.observables;

import co.encept.patternlockview.PatternLockView;
import co.encept.patternlockview.listener.PatternLockViewListener;
import co.encept.rxpatternlockview.events.PatternLockCompoundEvent;
import java.util.List;
import io.reactivex.rxjava3.android.MainThreadDisposable;
import io.reactivex.rxjava3.core.Observer;


/**
 * Created by aritraroy on 27/03/17.
 * Developed & Updated By Encept Ltd on 21/2023.
 */

public class PatternLockViewCompoundObservable
        extends BasePatternLockViewObservable<PatternLockCompoundEvent> {

    public PatternLockViewCompoundObservable(PatternLockView patternLockView, boolean emitInitialValue) {
        super(patternLockView, emitInitialValue);
    }

    @Override
    protected void subscribeActual(Observer<? super PatternLockCompoundEvent> observer) {
        subscribeListener(observer);
        if (mEmitInitialValue) {
            observer.onNext(new PatternLockCompoundEvent(PatternLockCompoundEvent.EventType.PATTERN_STARTED,
                    mPatternLockView.getPattern()));
        }
    }

    @Override
    protected void subscribeListener(Observer<? super PatternLockCompoundEvent> observer) {
        InternalListener internalListener = new InternalListener(mPatternLockView, observer);
        observer.onSubscribe(internalListener);
        mPatternLockView.addPatternLockListener(internalListener);
    }

    private static final class InternalListener extends MainThreadDisposable
            implements PatternLockViewListener {
        private final PatternLockView view;
        private final Observer<? super PatternLockCompoundEvent> observer;

        InternalListener(PatternLockView view, Observer<? super PatternLockCompoundEvent>
                observer) {
            this.view = view;
            this.observer = observer;
        }

        @Override
        public void onStarted() {
            if (!isDisposed()) {
                observer.onNext(new PatternLockCompoundEvent(PatternLockCompoundEvent
                        .EventType.PATTERN_STARTED, null));
            }
        }

        @Override
        public void onProgress(List<PatternLockView.Dot> progressPattern) {
            if (!isDisposed()) {
                observer.onNext(new PatternLockCompoundEvent(PatternLockCompoundEvent
                        .EventType.PATTERN_PROGRESS, progressPattern));
            }
        }

        @Override
        public void onComplete(List<PatternLockView.Dot> pattern) {
            if (!isDisposed()) {
                observer.onNext(new PatternLockCompoundEvent(PatternLockCompoundEvent
                        .EventType.PATTERN_COMPLETE, pattern));
            }
        }

        @Override
        public void onCleared() {
            if (!isDisposed()) {
                observer.onNext(new PatternLockCompoundEvent(PatternLockCompoundEvent
                        .EventType.PATTERN_CLEARED, null));
            }
        }

        @Override
        protected void onDispose() {
            view.removePatternLockListener(this);
        }
    }
}
