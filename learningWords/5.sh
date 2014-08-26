#!/bin/bash

# Tudom, hogy szebben is meg lehetett volna írni a kódot, de ez se rossz szerintem.
#
#
# BEÉPÍTETT PARAMÉTEREK
SEGITSEG="N"
#ELOBB="ANGOL"
ELOBB="MAGYAR"
VEGYES=0
ELSOSZUNET=2
MASODIKSZUNET=2
ELSOENTER=0
MASODIKENTER=0
MVOICE=0
AVOICE=0
SORBAN=0
KEZD=1
TART=100%
BEGIN=$KEZD
END=$TART
FAJL="inditasi_parameterek.conf"
KESZLET="adatok.text"
NAPLO1=0
NAPLO2=0
SZAMOTIS=1

if [ -f $FAJL ]
then
    if grep "^SZAMOTIS" $FAJL >/dev/null
    then
        SZAMOTIS=`grep "^SZAMOTIS" $FAJL | head -n 1 | cut -d "=" -f2`
    fi
    if [ $SZAMOTIS -ne 0 -a $SZAMOTIS -ne 1 ]
    then
        SZAMOTIS=1
    fi
fi


if [ -f $FAJL ]
then
    if grep "^NAPLO1" $FAJL >/dev/null
    then
        NAPLO1=`grep "^NAPLO1" $FAJL | head -n 1 | cut -d "=" -f2`
    fi
    if [ $NAPLO1 -ne 0 -a $NAPLO1 -ne 1 ]
    then
        NAPLO1=1
    fi
fi


if [ -f $FAJL ]
then
    if grep "^NAPLO2" $FAJL >/dev/null
    then
        NAPLO2=`grep "^NAPLO2" $FAJL | head -n 1 | cut -d "=" -f2`
    fi
    if [ $NAPLO2 -ne 0 -a $NAPLO2 -ne 1 ]
    then
        NAPLO2=1
    fi
fi


LOG1="futas.log"
LOG2="RANDOMNUMBER.log"

if [ $NAPLO1 -eq 1 ]
then
    echo "################" >> $LOG1
fi

helpe(){
    if grep "^HELP" $FAJL >/dev/null
    then
        HELP=`grep "^HELP" $FAJL | head -n 1 | cut -d "=" -f2 `
        if [ $HELP -eq 1 ]
        then
            echo
            echo -n "Kell segítség a paraméterezéshez? Ha igen, írj be egy \"i\" betűt: "
            read segitseg
            echo
            ST=`echo $segitseg | wc -c`
            if [ $ST -gt 1 ]
            then
                if [ $segitseg = "i" ]
                then
                    ./leiras.text
                fi
            fi
        fi
    fi
}



# KONFIGURÁCIÓS ÁLLOMÁNY ALAPJÁN TÖRTÉNŐ PARAMÉTER BEÁLLÍTÁSOK
#
# Sorrendben, vagy összevissza soroljam a kifejezéseket?
#
if [ -f inditasi_parameterek.conf ]
then

    if grep "^SORRENDBEN" inditasi_parameterek.conf > /dev/null
    then
        SBAN=`grep "^SORRENDBEN" inditasi_parameterek.conf | head -n 1 | cut -d "=" -f2`
        if [ $SBAN -eq 0 -o $SBAN -eq 1 ]
        then
            SORBAN=$SBAN
        fi
    fi
fi


#
# Melyik nyelvi változat kerüljön elsőként megjelenítésre?
#
    if grep "^ELSO" inditasi_parameterek.conf > /dev/null
    then
        LS=`grep "^ELSO" inditasi_parameterek.conf | head -n 1 | cut -d "=" -f2`
        if [ $LS = "MAGYAR" ]
        then
            ELOBB="MAGYAR"
        fi
        if [ $LS = "ANGOL" ]
        then
            ELOBB="ANGOL"
        fi
        if [ $LS = "VEGYES" ]
        then
            VEGYES=1
        fi
    fi


#
# Melyik nyelvi változat hangos felolvasását kérem?
#
    if grep "^HANGOS" inditasi_parameterek.conf | head -n 1 | cut -d "=" -f2 > /dev/null
    then
        HG=`grep "^HANGOS" inditasi_parameterek.conf | head -n 1 | cut -d "=" -f2`
        if [ $HG = "ANGOL" ]
        then
            AVOICE=1
            MVOICE=0
        fi
        if [ $HG = "MAGYAR" ]
        then
            AVOICE=0
            MVOICE=1
        fi
        if [ $HG = "EGYIKSEM" ]
        then
            MVOICE=0
            AVOICE=0
        fi
        if [ $HG = "MINDKETTO" ]
        then
            AVOICE=1
            MVOICE=1
        fi
    fi


#
# Az első és második nyelvi változatok kiírása között mennyi várakozási időt akarsz?
#

    if grep "^ELSO_IDO" inditasi_parameterek.conf | head -n 1 | cut -d "=" -f2 > /dev/null
    then
        EI=`grep "^ELSO_IDO" inditasi_parameterek.conf | head -n 1 | cut -d "=" -f2`
        if [ $EI = "ENTER" ]
        then
            ELSOENTER=1
            ELSOSZUNET=1
        else
            ELSOSZUNET=$EI
            ELSOENTER=0
        fi
    fi


#
# A kifejezéspár kiírása után mennyi időt várjak a következő kifejezéspár megjelenítésének
# megkezdésével?
#

    if grep "^MASODIK_IDO" inditasi_parameterek.conf | head -n 1 | cut -d "=" -f2 > /dev/null
    then
        MI=`grep "^MASODIK_IDO" inditasi_parameterek.conf | head -n 1 | cut -d "=" -f2`
        if [ $MI = "ENTER" ]
        then
            MASODIKENTER=1
            MASODIKSZUNET=1
        else
            MASODIKSZUNET=$MI
            MASODIKENTER=0
        fi
    fi


#
# Készletet tartalmazó fájl nevének meghatározása
#

    if grep "^KESZLET" inditasi_parameterek.conf | head -n 1 | cut -d "=" -f2 > /dev/null
    then
        KESZLET=`grep "^KESZLET" inditasi_parameterek.conf | head -n 1 | cut -d "=" -f2`
        if [ ! -f $KESZLET ]
        then
            echo -e "\nNézd át a konfigurációs állományt, mert nincs olyan készletfájl, amire hivatkoztál.\n"
            exit -1
        fi
    fi


#
# Válogatási tartomány kezdeti és végpontjának meghatározása. Mindkét érték benne foglaltatik a tartományban.
#

MAX_SOROK=`wc -l $KESZLET | cut -d " " -f1`
EGYSZAZALEK_FLOAT=`echo "scale=4; $MAX_SOROK / 100" | bc `

if [ $NAPLO1 -eq 1 ]
then
    echo "max sorok:   "$MAX_SOROK >> $LOG1
    echo "1 %:   "$EGYSZAZALEK_FLOAT >> $LOG1
fi

if grep "^TART" $FAJL | head -n 1 | cut -d "=" -f2 2>&1   >/dev/null
then
    PARAM2=`grep "^TART" $FAJL | head -n 1 | cut -d "=" -f2 `
    TRT=$PARAM2
    if echo $PARAM2 | grep "%" >/dev/null
    then
        PARAM2=`echo $PARAM2 | tr -d "%"`
        if [ $PARAM2 -gt 100 -o $PARAM2 -le 1 ]
        then
            END=$MAX_SOROK
        else
            END=`echo  "$PARAM2 * $EGYSZAZALEK_FLOAT" | bc | cut -d "." -f1`
        fi
    else
        if [ $PARAM2 -gt $MAX_SOROK -o $PARAM2 -le 1 ]
        then
            END=$MAX_SOROK
        else
            END=$PARAM2
        fi
    fi
fi


if grep "^KEZD" $FAJL | head -n 1 | cut -d "=" -f2 2>&1   >/dev/null
then
    PARAM1=`grep "^KEZD" $FAJL | head -n 1 | cut -d "=" -f2 `
    if [ $NAPLO1 -eq 1 ]
    then
        echo "KEZD: "$PARAM1 >> $LOG1
        echo "TART: "$TRT >> $LOG1
    fi
    if grep "^KEZD" $FAJL | head -n 1 | cut -d "=" -f2 | grep "-" >/dev/null
    then
        PARAM1=`echo $PARAM1 | tr -d "-" | tr -d "%"`
        if [ $PARAM1 -gt $END -o $PARAM1 -eq 0 ]
        then
            BEGIN=1
        else
            BEGIN=`expr $END - $PARAM1 + 1`
        fi
    else
        if echo $PARAM1 | grep "%" >/dev/null
        then
            PARAM1=`echo $PARAM1 | tr -d "%"`
            if [ $PARAM1 -lt 2 -o $PARAM1 -gt 99 ]
            then
                BEGIN=1
            else
                BEGIN=`echo  "$PARAM1 * $EGYSZAZALEK_FLOAT" | bc | cut -d "." -f1`
            fi
        else
            BEGIN=$PARAM1
        fi
    fi
fi

if [ $NAPLO1 -eq 1 ]
then
    echo "BEGIN:   "$BEGIN >> $LOG1
    echo "END:   "$END >> $LOG1
fi


if [ $# -ge 8 ]
then
    PARAMCL2=`echo $8 | tr -dc "0-9%"`
    TRTCL=$PARAMCL2
    if echo $PARAMCL2 | grep "%" >/dev/null
    then
        PARAMCL2=`echo $PARAMCL2 | tr -d "%"`
        if [ $PARAMCL2 -gt 100 -o $PARAMCL2 -le 1 ]
        then
            END=$MAX_SOROK
        else
            END=`echo  "$PARAMCL2 * $EGYSZAZALEK_FLOAT" | bc | cut -d "." -f1`
        fi
    else
        if [ $PARAMCL2 -gt $MAX_SOROK -o $PARAMCL2 -le 1 ]
        then
            END=$MAX_SOROK
        else
            END=$PARAMCL2
        fi
    fi
fi

if [ $# -ge 7 ]
then
    PARAMCL1=`echo $7 | tr -cd "0-9%\-"`
    if echo $PARAMCL1 | grep "-" >/dev/null 2>&1
    then
        PARAMCL1=`echo $PARAMCL1 | tr -d "-" | tr -d "%"`
        if [ $PARAMCL1 -gt $END -o $PARAMCL1 -eq 0 ]
        then
            BEGIN=1
        else
            BEGIN=`expr $END - $PARAMCL1 + 1`
        fi
    else
        if echo $PARAMCL1 | grep "%" >/dev/null
        then
            PARAMCL1=`echo $PARAMCL1 | tr -d "%"`
            if [ $PARAMCL1 -lt 2 -o $PARAMCL1 -gt 99 ]
            then
                BEGIN=1
            else
                BEGIN=`echo  "$PARAMCL1 * $EGYSZAZALEK_FLOAT" | bc | cut -d "." -f1`
            fi
        else
            BEGIN=$PARAMCL1
        fi
    fi
fi



if [ $# -ge 7 ]
then
    if [ $NAPLO1 -eq 1 ]
    then
        echo "BEGIN COMMANDLINE:   "$BEGIN >> $LOG1
    fi
fi
if [ $# -ge 8 ]
then
    if [ $NAPLO1 -eq 1 ]
    then
        echo "END COMMANDLINE:   "$END >> $LOG1
    fi
fi


if [ $BEGIN -ge $END ]
then
    if [ $NAPLO1 -eq 1 ]
    then
        echo "BEGIN ("$BEGIN") nagyobb, vagy egyenlo, mint a vegpont END ("$END"), ezert modositom a BEGIN es END, kezdeti es vegpontot" >> $LOG1
    fi
    BEGIN=1
    END=$MAX_SOROK
    if [ $NAPLO1 -eq 1 ]
    then
        echo "BEGIN-mod:   "$BEGIN >> $LOG1
        echo "END-mod:   "$END >> $LOG1
    fi
fi

TARTOMANY=`expr $END - $BEGIN + 1`
if [ $NAPLO1 -eq 1 ]
then
    echo "TARTOMANY: "$TARTOMANY >> $LOG1
fi

if [ $# -ge 1 ] 
then
    if [ $1 = "h" ]
    then
        ./leiras.text
    else
        if [ $1 = "v" ]
        then
            VEGYES=1
        fi
        if [ $1 = "a" ]
        then
            ELOBB="ANGOL"
        fi
        if [ $1 = "m" ]
        then
            ELOBB="MAGYAR"
        fi
        helpe
    fi
else
    helpe
fi

# PARANCSSORI PARAMÉTEREK ELEMZÉSE

if [ $# -ge 2 ]
then
    ELSOSZUNET=$2
fi

if [ $# -ge 3 ]
then
    MASODIKSZUNET=$3
fi


if [ $# -ge 4 ]
then
    if [ $4 = "1" ]
    then
        ELSOENTER=1
    fi

    if [ $4 = "2" ]
    then
        MASODIKENTER=1
    fi

    if [ $4 = "12" ]
    then
        ELSOENTER=1
        MASODIKENTER=1
    fi
fi

if [ $# -ge 5 ]
then
    if [  $5 = "vam"  -o  $5 = "vma"  ]
    then
        AVOICE=1
        MVOICE=1
    fi
    if [ $5 = "va" ]
    then
        AVOICE=1
    fi
    if [ $5 = "vm" ]
    then
        MVOICE=1
    fi
fi

if [ $# -ge 6 ]
then
    if [ $6 = "s" ]
    then
        SORBAN=1
    else
        SORBAN=0
    fi
fi

if [ $# -ge 9 ]
then
    if [ -f $9 ]
    then
        FAJL=$9
    else
        echo -e "\nNincs ilyen fájl...Kilépek...\n"
        exit -1
    fi
fi


#if [ $# -ge 7 ]
#then
#    if [ $7 -lt 0 -o $7 -gt 99 ]
#    then
#        KEZD=99
#    else
#        KEZD=$7
#    fi
#fi

#if [ $# -ge 8 ]
#then
#    if [ $8 -le $KEZD -o $8 -gt 100 ]
#    then
#        TART=100
#    else
#        TART=$8
#    fi
#fi


while [ 1 ]
do
    if [ $SORBAN -eq 0 ]
    then
        VV=$RANDOM
        let "VV %= 2"
        S=$RANDOM
        let "S %= $TARTOMANY"
        S=`expr $S + $BEGIN`
        if [ $NAPLO2 -eq 1 ]
        then
            echo $S >> $LOG2
        fi
        SH=`echo $S | wc -c`
        SH=`expr $SH - 1`
        KULONBSEG=`expr 5 - $SH`
        for i in `seq 1 $KULONBSEG`
        do
            S=`echo "0"$S`
        done


        ANGOL_SOR=`grep ^$S adatok.text | cut -c 7- | cut -d "#" -f1 | sed s/" sg "/" something "/g | sed s/" sy "/" somebody "/g`
        MAGYAR_SOR=`grep ^$S adatok.text | cut -c 7- | cut -d "#" -f2`
        clear
        if [ $SZAMOTIS -eq 1 ]
        then
            echo $S
        fi
        echo
        echo -n "  "
        if [ $VEGYES -eq 1 ]
        then
            if [ $VV -eq 0 ]
            then
                ELOBB="MAGYAR"
            else
                ELOBB="ANGOL"
            fi
        fi
        if test $ELOBB = "MAGYAR"
        then
            echo $MAGYAR_SOR
            if test $MVOICE -eq 1
            then
                espeak -v hu "$MAGYAR_SOR"
            fi
        else
            echo $ANGOL_SOR
            if test $AVOICE -eq 1
            then
                espeak -v  en "$ANGOL_SOR"
            fi
        fi
        if test $ELSOENTER -eq 1
        then
            read e
            e=`echo "2"$e`
        else
            sleep $ELSOSZUNET
        fi
        echo
        echo -n "  "
        if test $ELOBB = "MAGYAR"
        then
            echo $ANGOL_SOR
            if test $AVOICE -eq 1
            then
                espeak -v  en "$ANGOL_SOR"
            fi
        else
            echo $MAGYAR_SOR
            if test $MVOICE -eq 1
            then
                espeak -v hu "$MAGYAR_SOR"
            fi
        fi
        echo
        if test $MASODIKENTER -eq 1
        then
            read e
            e=`echo "2"$e`
        else
            sleep $MASODIKSZUNET
        fi

    else
        VV=$RANDOM
        let "VV %= 2"
        for i in `seq -w $BEGIN $END`
        do
        SH=`echo $i | wc -c`
        SH=`expr $SH - 1`
        #echo "SH: "$SH
        #echo "i: "$i
        KULONBSEG=`expr 5 - $SH`
        S=""
            for T in `seq 1 $KULONBSEG`
            do
                S=`echo "0"$S`
            done
        S=`echo $S$i`

        ANGOL_SOR=`grep ^$S adatok.text | cut -c 7- | cut -d "#" -f1 | sed s/" sg "/" something "/g | sed s/" sy "/" somebody "/g`
        MAGYAR_SOR=`grep ^$S adatok.text | cut -c 7- | cut -d "#" -f2`
        clear
        if [ $SZAMOTIS -eq 1 ]
        then
            echo $S
        fi
        echo
        echo -n "  "
        if [ $VEGYES -eq 1 ]
        then
            if [ $VV -eq 0 ]
            then
                ELOBB="MAGYAR"
            else
                ELOBB="ANGOL"
            fi
        fi
        if test $ELOBB = "MAGYAR"
        then
            echo $MAGYAR_SOR
            if test $MVOICE -eq 1
            then
                espeak -v hu "$MAGYAR_SOR"
            fi
        else
            echo $ANGOL_SOR
            if test $AVOICE -eq 1
            then
                espeak -v  en "$ANGOL_SOR"
            fi
        fi
        if test $ELSOENTER -eq 1
        then
            read e
            e=`echo "2"$e`
        else
            sleep $ELSOSZUNET
        fi
        echo
        echo -n "  "
        if test $ELOBB = "MAGYAR"
        then
            echo $ANGOL_SOR
            if test $AVOICE -eq 1
            then
                espeak -v  en "$ANGOL_SOR"
            fi
        else
            echo $MAGYAR_SOR
            if test $MVOICE -eq 1
            then
                espeak -v hu "$MAGYAR_SOR"
            fi
        fi
        echo
        if test $MASODIKENTER -eq 1
        then
            read e
            e=`echo "2"$e`
        else
            sleep $MASODIKSZUNET
        fi

        done
    fi
done