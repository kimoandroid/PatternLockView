package co.encept.rxpatternlockview.observables;

import co.encept.patternlockview.PatternLockView;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;

/**
 * Created by aritraroy on 01/04/17.
 * Developed & Updated By Encept Ltd on 21/2023.
 */

public abstract class BasePatternLockViewObservable<BasePatternLockEvent>
        extends Observable<BasePatternLockEvent> {
    protected PatternLockView mPatternLockView;
    protected boolean mEmitInitialValue;

    protected BasePatternLockViewObservable(PatternLockView patternLockView, boolean emitInitialValue) {
        mPatternLockView = patternLockView;
        mEmitInitialValue = emitInitialValue;
    }

    protected abstract void subscribeListener(Observer<? super BasePatternLockEvent> observer);
}
