package co.encept.rxpatternlockview.events;

import co.encept.patternlockview.PatternLockView;

import java.util.List;

/**
 * Created by aritraroy on 01/04/17.
 * Developed & Updated By Encept Ltd on 21/2023.
 */

public class PatternLockCompleteEvent extends BasePatternLockEvent {

    public PatternLockCompleteEvent(List<PatternLockView.Dot> pattern) {
        super(pattern);
    }
}
