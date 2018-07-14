#include <string.h>
#include <jni.h>

#define CORRECTPASSWORD "s3cr3tp4ss"
#define HALFSTRING1 "h3r3"


char * replace( char *s, char ch, char repl);


JNIEXPORT jstring JNICALL
Java_com_example_reverzeme_ChallengeJNI_stringFromJNI(JNIEnv *env, jobject instance) {


    return (*env)->NewStringUTF(env, CORRECTPASSWORD);
}

JNIEXPORT jstring JNICALL
Java_com_example_reverzeme_ChallengeJNI_stringOtherHalfKey(JNIEnv *env, jobject instance, jstring extraString_) {

    // TODO
    const char *rem = (*env)->GetStringUTFChars(env, extraString_, 0);

    char s1[50] = "&^$@(#@~%%)";
    strcat(s1,HALFSTRING1);

    char *z1 = replace(s1, '&', 'o');
    char *z2 = replace(z1, '^', 'H');
    char *z3 = replace(z2, '#', 's');
    char *z4 = replace(z3, '$', 'y');
    char *z5 = replace(z4, '@', '0');
    char *z6 = replace(z5, '(', 'u');
    char *z7 = replace(z6, '~', '1');
    char *z8 = replace(z7, ')', '7');
    char *z9 = replace(z8, '%', '3');
    strcat(z9,rem);

    return (*env)->NewStringUTF(env, z9);
}
JNIEXPORT char * replace( char *s, char ch, char repl) {
    int i=0;
    while(s[i]!='\0')
    {
        if(s[i]==ch)
        {
            s[i]=repl;
        }
        i++;
    }
    return s;

}
