package co.encept.rxpatternlockview.utils;

import android.os.Looper;
import io.reactivex.rxjava3.core.Observer;

/**
 * Created by aritraroy on 02/04/17.
 * Developed & Updated By Encept Ltd on 21/2023.
 */

public final class Preconditions {

    private Preconditions() {
        throw new AssertionError("You can not instantiate this class. Use its static utility " +
                "methods instead");
    }

    public static void checkNotNull(Object value, String message) {
        if (value == null) {
            throw new NullPointerException(message);
        }
    }

    public static boolean checkMainThread(Observer<?> observer) {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            observer.onError(new IllegalStateException(
                    "Expected to be called on the main thread but was " + Thread.currentThread().getName()));
            return false;
        }
        return true;
    }
}