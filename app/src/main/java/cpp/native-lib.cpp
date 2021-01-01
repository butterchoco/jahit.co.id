#include <jni.h>
#include <string>

extern "C" JNIEXPORT jlong JNICALL
Java_id_ac_ui_cs_mobileprogramming_ahmadsupriyanto_belajarfilm_MainActivity_diffDateFromJNI(
        JNIEnv* env,
        jobject,
        int jminutestart,
        int jhourstart,
        int jdaystart,
        jlong jmonthstart,
        jlong jyearstart,
        int jdayend,
        jlong jmonthend,
        jlong jyearend) {
    u_long diffYearInMills = (jyearend - jyearstart)*31556952000;
    u_long diffMonthInMills = (jmonthend - jmonthstart)*2628000000;
    int diffDaysInMills = (jdayend - jdaystart)*86400000;
    int diffHoursInMills = (24 - jhourstart) * 3600000;
    int diffMinutesInMills = (60 - jminutestart) * 60000;
    int duration = diffYearInMills + diffMonthInMills + diffDaysInMills + diffHoursInMills + diffMinutesInMills;
    return duration;
}