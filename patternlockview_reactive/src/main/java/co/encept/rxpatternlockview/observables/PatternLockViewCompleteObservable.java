package co.encept.rxpatternlockview.observables;

import co.encept.patternlockview.PatternLockView;
import co.encept.patternlockview.listener.PatternLockViewListener;
import co.encept.rxpatternlockview.events.PatternLockCompleteEvent;
import java.util.List;
import io.reactivex.rxjava3.android.MainThreadDisposable;
import io.reactivex.rxjava3.core.Observer;

/**
 * Created by aritraroy on 01/04/17.
 * Developed & Updated By Encept Ltd on 21/2023.
 */

public class PatternLockViewCompleteObservable extends
        BasePatternLockViewObservable<PatternLockCompleteEvent> {

    public PatternLockViewCompleteObservable(PatternLockView patternLockView, boolean emitInitialValues) {
        super(patternLockView, emitInitialValues);
    }

    @Override
    protected void subscribeListener(Observer<? super PatternLockCompleteEvent> observer) {
        InternalListener internalListener = new InternalListener(mPatternLockView, observer);
        observer.onSubscribe(internalListener);
        mPatternLockView.addPatternLockListener(internalListener);
    }

    @Override
    protected void subscribeActual(Observer<? super PatternLockCompleteEvent> observer) {
        subscribeListener(observer);
        if (mEmitInitialValue) {
            observer.onNext(new PatternLockCompleteEvent(mPatternLockView.getPattern()));
        }
    }

    private static final class InternalListener extends MainThreadDisposable
            implements PatternLockViewListener {
        private final PatternLockView view;
        private final Observer<? super PatternLockCompleteEvent> observer;

        InternalListener(PatternLockView view, Observer<? super PatternLockCompleteEvent> observer) {
            this.view = view;
            this.observer = observer;
        }

        @Override
        public void onStarted() {

        }

        @Override
        public void onProgress(List<PatternLockView.Dot> progressPattern) {

        }

        @Override
        public void onComplete(List<PatternLockView.Dot> pattern) {
            if (!isDisposed()) {
                observer.onNext(new PatternLockCompleteEvent(pattern));
            }
        }

        @Override
        public void onCleared() {

        }

        @Override
        protected void onDispose() {
            view.removePatternLockListener(this);
        }
    }
}
