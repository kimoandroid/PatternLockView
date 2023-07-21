package co.encept.rxpatternlockview;

import co.encept.patternlockview.PatternLockView;
import co.encept.rxpatternlockview.events.PatternLockCompleteEvent;
import co.encept.rxpatternlockview.events.PatternLockCompoundEvent;
import co.encept.rxpatternlockview.events.PatternLockProgressEvent;
import co.encept.rxpatternlockview.observables.PatternLockViewCompleteObservable;
import co.encept.rxpatternlockview.observables.PatternLockViewCompoundObservable;
import co.encept.rxpatternlockview.observables.PatternLockViewProgressObservable;
import co.encept.rxpatternlockview.utils.Preconditions;
import io.reactivex.rxjava3.core.Observable;

/**
 * Created by aritraroy on 27/03/17.
 * Developed & Updated By Encept Ltd on 21/2023.
 */

public class RxPatternLockView {

    /**
     * Create an observable for all events of this {@code view}.
     * <p>
     * <em>Warning:</em> The created observable keeps a strong reference to {@code view}.
     * Unsubscribe to free this reference.
     */
    public static Observable<PatternLockCompoundEvent> patternChanges(PatternLockView patternLockView) {
        Preconditions.checkNotNull(patternLockView, "view == null");
        return new PatternLockViewCompoundObservable(patternLockView, false);
    }

    /**
     * Create an observable for all events of this {@code view}.
     * <p>
     * <em>Warning:</em> The created observable keeps a strong reference to {@code view}.
     * Unsubscribe to free this reference.
     * <p>
     * <em>Note:</em> A value will be emitted immediately on subscribe.
     */
    public static Observable<PatternLockCompoundEvent> patternChanges(PatternLockView patternLockView,
                                                                      boolean emitInitialValue) {
        Preconditions.checkNotNull(patternLockView, "view == null");
        return new PatternLockViewCompoundObservable(patternLockView, emitInitialValue);
    }

    /**
     * Create an observable for only the pattern complete event of this {@code view}.
     * <p>
     * <em>Warning:</em> The created observable keeps a strong reference to {@code view}.
     * Unsubscribe to free this reference.
     */
    public static Observable<PatternLockCompleteEvent> patternComplete(PatternLockView patternLockView) {
        Preconditions.checkNotNull(patternLockView, "view == null");
        return new PatternLockViewCompleteObservable(patternLockView, false);
    }

    /**
     * Create an observable for only the pattern complete event of this {@code view}.
     * <p>
     * <em>Warning:</em> The created observable keeps a strong reference to {@code view}.
     * Unsubscribe to free this reference.
     * <p>
     * <em>Note:</em> A value will be emitted immediately on subscribe.
     */
    public static Observable<PatternLockCompleteEvent> patternComplete(PatternLockView patternLockView,
                                                                       boolean emitInitialValues) {
        Preconditions.checkNotNull(patternLockView, "view == null");
        return new PatternLockViewCompleteObservable(patternLockView, emitInitialValues);
    }

    /**
     * Create an observable for only the pattern progress event of this {@code view}.
     * <p>
     * <em>Warning:</em> The created observable keeps a strong reference to {@code view}.
     * Unsubscribe to free this reference.
     */
    public static Observable<PatternLockProgressEvent> patternProgress(PatternLockView patternLockView) {
        Preconditions.checkNotNull(patternLockView, "view == null");
        return new PatternLockViewProgressObservable(patternLockView, false);
    }

    /**
     * Create an observable for only the pattern progress event of this {@code view}.
     * <p>
     * <em>Warning:</em> The created observable keeps a strong reference to {@code view}.
     * Unsubscribe to free this reference.
     * <p>
     * <em>Note:</em> A value will be emitted immediately on subscribe.
     */
    public static Observable<PatternLockProgressEvent> patternProgress(PatternLockView patternLockView,
                                                                       boolean emitInitialValues) {
        Preconditions.checkNotNull(patternLockView, "view == null");
        return new PatternLockViewProgressObservable(patternLockView, emitInitialValues);
    }
}
