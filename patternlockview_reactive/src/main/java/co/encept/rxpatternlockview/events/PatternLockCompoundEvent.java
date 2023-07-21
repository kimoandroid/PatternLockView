package co.encept.rxpatternlockview.events;

import androidx.annotation.IntDef;

import co.encept.patternlockview.PatternLockView;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

import static co.encept.rxpatternlockview.events.PatternLockCompoundEvent.EventType.PATTERN_CLEARED;
import static co.encept.rxpatternlockview.events.PatternLockCompoundEvent.EventType.PATTERN_COMPLETE;
import static co.encept.rxpatternlockview.events.PatternLockCompoundEvent.EventType.PATTERN_PROGRESS;
import static co.encept.rxpatternlockview.events.PatternLockCompoundEvent.EventType.PATTERN_STARTED;

/**
 * Created by aritraroy on 27/03/17.
 * Developed & Updated By Encept Ltd on 21/2023.
 */

public final class PatternLockCompoundEvent extends BasePatternLockEvent {

    @IntDef({PATTERN_STARTED, PATTERN_PROGRESS, PATTERN_COMPLETE, PATTERN_CLEARED})
    @Retention(RetentionPolicy.SOURCE)
    public @interface EventType {
        int PATTERN_STARTED = 0;
        int PATTERN_PROGRESS = 1;
        int PATTERN_COMPLETE = 2;
        int PATTERN_CLEARED = 3;
    }

    private final int mEventType;

    public PatternLockCompoundEvent(@EventType int eventType, List<PatternLockView.Dot> pattern) {
        super(pattern);
        mEventType = eventType;
    }

    @EventType
    public int getEventType() {
        return mEventType;
    }
}
