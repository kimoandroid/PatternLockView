package co.encept.rxpatternlockview.events;

import androidx.annotation.Nullable;
import co.encept.patternlockview.PatternLockView;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by aritraroy on 01/04/17.
 * Developed & Updated By Encept Ltd on 21/2023.
 */

public abstract class BasePatternLockEvent {
    protected List<PatternLockView.Dot> mPattern;

    protected BasePatternLockEvent(List<PatternLockView.Dot> pattern) {
        mPattern = pattern;
    }

    @Nullable
    public List<PatternLockView.Dot> getPattern() {
        if (mPattern == null) {
            return new ArrayList<>();
        }
        return new ArrayList<>(mPattern);
    }
}
