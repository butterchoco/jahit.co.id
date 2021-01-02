#include <jni.h>
#include <string>

void sumDayFromMonth(jlong month, jlong *day, jlong isKabisatY);
void sumDayFromYears(jlong year, jlong *day, jlong isKabisatY);
jlong isKabisat(jlong year);


extern "C" JNIEXPORT jlong JNICALL
Java_id_ac_ui_cs_mobileprogramming_ahmadsupriyanto_belajarfilm_MainActivity_diffDateFromJNI(
        JNIEnv* env,
        jobject,
        jlong jminutestart,
        jlong jhourstart,
        jlong jdaystart,
        jlong jmonthstart,
        jlong jyearstart,
        jlong jminuteend,
        jlong jhourend,
        jlong jdayend,
        jlong jmonthend,
        jlong jyearend)
{
    jlong isKabisatYStart = isKabisat(jyearstart);
    sumDayFromYears(jyearstart, &jdaystart, isKabisatYStart);
    sumDayFromMonth(jmonthstart, &jdaystart, isKabisatYStart);

    jlong isKabisatYEnd = isKabisat(jyearend);
    sumDayFromYears(jyearend, &jdayend, isKabisatYEnd);
    sumDayFromMonth(jmonthend, &jdayend, isKabisatYEnd);

    jlong diffDaysInMills = (jdayend - jdaystart)*86400000;
    jlong diffHoursInMills = (jhourend - jhourstart)*3600000;
    jlong diffMinutesInMills = (jminuteend - jminutestart)*60000;
    return diffDaysInMills + diffHoursInMills + diffMinutesInMills;
}

void sumDayFromMonth(jlong month, jlong *day, jlong isKabisatY) {
    month--;
    if (month <= 7) {
        if (month > 1) {
            month -= 2;
            *day += 59 + isKabisatY;
            if (month % 2 == 1) {
                month--;
                *day += 31;
            }
        } else {
            *day += 31*month;
        }
        *day += (month / 2) * 61;
    } else {
        month -= 7;
        if (month > 1) {
            month -= 2;
            *day += 59 + isKabisatY;
            if (month % 2 == 1) {
                month--;
                *day += 31;
            }
        } else {
            *day += 31*month;
        }
        *day += (month/2)*61 + (3*61+31);
    }

}

void sumDayFromYears(jlong year, jlong *day, jlong isKabisatY) {
    if (isKabisatY == 1) *day += (year/4)*(365*3+366);
    else {
        year--;
        *day += (year % 4) * 365 + 366;
        year -= year % 4;
        *day += (year/4)*(365*3+366);
    }
}

jlong isKabisat(jlong year) { return year % 4 == 0; }