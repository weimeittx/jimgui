package org.ice1000.jimgui.flag;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface Flag {
    int get();

    /**
     *
     * @param flag to check for
     * @param flags to check if flag is contained within
     * @return boolean true of flag was found
     */
    static boolean hasFlag(int flag, @NotNull Flag @NotNull ... flags) {
        return Arrays.stream(flags).anyMatch(value -> value.get() == flag);
    }

    // Slot enum[0], emum[1] for flags should be nothing and no flag found

    /**
     * This method assumes that all Flag.Type classes have a enum at position 0
     * labeled NoSuchFlag that takes the default or nothing value for error
     * prevention.
     *
     * @param enumType class that and enum type that extends Flag
     * @param flag the flag to lookup
     * @param <E> the Flag which extends Enum
     * @return the Flag which is the flag int value
     */
    static <E extends @NotNull Flag> @NotNull E reverseLookup(@NotNull Class<@NotNull E> enumType, int flag) {
        E[] enumConstants = enumType.getEnumConstants();
        for (int i = 1; i < enumConstants.length; i++) {
            E enumConstant = enumConstants[i];
            int flagConstant = enumConstant.get();
            if (flagConstant == flag) return enumConstant;
        }
        return enumConstants[0];
    }

    /**
     * This method assumes that all Flag.Type classes have a enum at position 0
     * labeled NoSuchFlag that takes the default or nothing value for error
     * prevention.
     *
     * @param enumType class that and enum type that extends Flag
     * @param flags the flags to lookup
     * @param <E> the Flag which extends Enum
     * @return the flags which is the flags int values
     */
    static <E extends @NotNull Flag> @NotNull E @NotNull [] getAsFlags(@NotNull Class<@NotNull E> enumType, int flags) {
        List<E> setFlags = new ArrayList<>();
        E[] enumConstants = enumType.getEnumConstants();
        for (int i = 1; i < enumConstants.length; i++) {
            E enumConstant = enumConstants[i];
            int flagConstant = enumConstant.get();
            boolean flagSet = (flags & flagConstant) != 0;
            if (flagSet) setFlags.add(enumConstant);
        }
        final E[] a = (E[]) Array.newInstance(enumType, setFlags.size());
        setFlags.toArray(a);
        return a;
    }

    /**
     *
     * @param flagSelection Flags
     * @return value of all the flags set
     */
    static int getAsValue(@NotNull Flag @NotNull... flagSelection){
        int flags = 0;
        for (Flag flag : flagSelection) {
            flags |= flag.get();
        }
        return flags;
    }
}