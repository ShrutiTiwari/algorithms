package com.aqua.music.example;

public class Note {

    public int pitch = 0;
    public int instrument = 0;
    public int part = 4;
    public int velocity = 127;
    int counter16 = 0;
    int channel = 0;
    boolean on = false;
    public Note(int prt, int ptch, int i, int v) {
        part = prt;
        instrument = i;
        velocity = v;
        pitch = ptch;
    }
    public Note(int prt, int ptch, int i) {
        part = prt;
        instrument = i;
        pitch = ptch;
    }
    public void play() {
        channel = Tools.openNote(instrument, pitch, velocity);
        counter16 = 0;
        on = true;
    }
    public void stop() {
        Tools.closeNote(pitch, channel);
        counter16 = 0;
        on = false;

    }
    public void tick() {
        if (on) {
            counter16++;
            int pp = 16 / part;
            if (counter16 >= pp) {
                stop();
            }
        }
    }
    public static int i0_Acoustic_Grand_Piano = 0;
    public static int i1_Bright_Acoustic_Piano = 1;
    public static int i2_Electric_Grand_Piano = 2;
    public static int i3_Honky_tonk_Piano = 3;
    public static int i4_Electric_Piano_1 = 4;
    public static int i5_Electric_Piano_2 = 5;
    public static int i6_Harpsichord = 6;
    public static int i7_Clavi = 7;
    public static int i8_Celesta = 8;
    public static int i9_Glockenspiel = 9;
    public static int i10_Music_Box = 10;
    public static int i11_Vibraphone = 11;
    public static int i12_Marimba = 12;
    public static int i13_Xylophone = 13;
    public static int i14_Tubular_Bells = 14;
    public static int i15_Dulcimer = 15;
    public static int i16_Drawbar_Organ = 16;
    public static int i17_Percussive_Organ = 17;
    public static int i18_Rock_Organ = 18;
    public static int i19_Church_Organ = 19;
    public static int i20_Reed_Organ = 20;
    public static int i21_Accordion = 21;
    public static int i22_Harmonica = 22;
    public static int i23_Tango_Accordion = 23;
    public static int i24_Acoustic_Guitar_nylon = 24;
    public static int i25_Acoustic_Guitar_steel = 25;
    public static int i26_Electric_Guitar_jazz = 26;
    public static int i27_Electric_Guitar_clean = 27;
    public static int i28_Electric_Guitar_muted = 28;
    public static int i29_Overdriven_Guitar = 29;
    public static int i30_Distortion_Guitar = 30;
    public static int i31_Guitar_harmonics = 31;
    public static int i32_Acoustic_Bass = 32;
    public static int i33_Electric_Bass_finger = 33;
    public static int i34_Electric_Bass_pick = 34;
    public static int i35_Fretless_Bass = 35;
    public static int i36_Slap_Bass_1 = 36;
    public static int i37_Slap_Bass_2 = 37;
    public static int i38_Synth_Bass_1 = 38;
    public static int i39_Synth_Bass_2 = 39;
    public static int i40_Violin = 40;
    public static int i41_Viola = 41;
    public static int i42_Cello = 42;
    public static int i43_Contrabass = 43;
    public static int i44_Tremolo_Strings = 44;
    public static int i45_Pizzicato_Strings = 45;
    public static int i46_Orchestral_Harp = 46;
    public static int i47_Timpani = 47;
    public static int i48_String_Ensemble_1 = 48;
    public static int i49_String_Ensemble_2 = 49;
    public static int i50_SynthStrings_1 = 50;
    public static int i51_SynthStrings_2 = 51;
    public static int i52_Choir_Aahs = 52;
    public static int i53_Voice_Oohs = 53;
    public static int i54_Synth_Voice = 54;
    public static int i55_Orchestra_Hit = 55;
    public static int i56_Trumpet = 56;
    public static int i57_Trombone = 57;
    public static int i58_Tuba = 58;
    public static int i59_Muted_Trumpet = 59;
    public static int i60_French_Horn = 60;
    public static int i61_Brass_Section = 61;
    public static int i62_SynthBrass_1 = 62;
    public static int i63_SynthBrass_2 = 63;
    public static int i64_Soprano_Sax = 64;
    public static int i65_Alto_Sax = 65;
    public static int i66_Tenor_Sax = 66;
    public static int i67_Baritone_Sax = 67;
    public static int i68_Oboe = 68;
    public static int i69_English_Horn = 69;
    public static int i70_Bassoon = 70;
    public static int i71_Clarinet = 71;
    public static int i72_Piccolo = 72;
    public static int i73_Flute = 73;
    public static int i74_Recorder = 74;
    public static int i75_Pan_Flute = 75;
    public static int i76_Blown_Bottle = 76;
    public static int i77_Shakuhachi = 77;
    public static int i78_Whistle = 78;
    public static int i79_Ocarina = 79;
    public static int i80_Lead_1_square = 80;
    public static int i81_Lead_2_sawtooth = 81;
    public static int i82_Lead_3_calliope = 82;
    public static int i83_Lead_4_chiff = 83;
    public static int i84_Lead_5_charang = 84;
    public static int i85_Lead_6_voice = 85;
    public static int i86_Lead_7_fifths = 86;
    public static int i87_Lead_8_bass_lead = 87;
    public static int i88_Pad_1_new_age = 88;
    public static int i89_Pad_2_warm = 89;
    public static int i90_Pad_3_polysynth = 90;
    public static int i91_Pad_4_choir = 91;
    public static int i92_Pad_5_bowed = 92;
    public static int i93_Pad_6_metallic = 93;
    public static int i94_Pad_7_halo = 94;
    public static int i95_Pad_8_sweep = 95;
    public static int i96_FX_1_rain = 96;
    public static int i97_FX_2_soundtrack = 97;
    public static int i98_FX_3_crystal = 98;
    public static int i99_FX_4_atmosphere = 99;
    public static int i100_FX_5_brightness = 100;
    public static int i101_FX_6_goblins = 101;
    public static int i102_FX_7_echoes = 102;
    public static int i103_FX_8_sci_fi = 103;
    public static int i104_Sitar = 104;
    public static int i105_Banjo = 105;
    public static int i106_Shamisen = 106;
    public static int i107_Koto = 107;
    public static int i108_Kalimba = 108;
    public static int i109_Bag_pipe = 109;
    public static int i110_Fiddle = 110;
    public static int i111_Shanai = 111;
    public static int i112_Tinkle_Bell = 112;
    public static int i113_Agogo = 113;
    public static int i114_Steel_Drums = 114;
    public static int i115_Woodblock = 115;
    public static int i116_Taiko_Drum = 116;
    public static int i117_Melodic_Tom = 117;
    public static int i118_Synth_Drum = 118;
    public static int i119_Reverse_Cymbal = 119;
    public static int i120_Guitar_Fret_Noise = 120;
    public static int i121_Breath_Noise = 121;
    public static int i122_Seashore = 122;
    public static int i123_Bird_Tweet = 123;
    public static int i124_Telephone_Ring = 124;
    public static int i125_Helicopter = 125;
    public static int i126_Applause = 126;
    public static int i127_Gunshot = 127;
    public static int p0_0_Do = 0;
    public static int p1_0_Do_Diese = 1;
    public static int p2_0_Re = 2;
    public static int p3_0_Re_Diese = 3;
    public static int p4_0_Mi = 4;
    public static int p5_0_Fa = 5;
    public static int p6_0_Fa_Diese = 6;
    public static int p7_0_Sol = 7;
    public static int p8_0_Sol_Diese = 8;
    public static int p9_0_La = 9;
    public static int p10_0_La_Diese = 10;
    public static int p11_0_Si = 11;
    public static int p12_1_Do = 12;
    public static int p13_1_Do_Diese = 13;
    public static int p14_1_Re = 14;
    public static int p15_1_Re_Diese = 15;
    public static int p16_1_Mi = 16;
    public static int p17_1_Fa = 17;
    public static int p18_1_Fa_Diese = 18;
    public static int p19_1_Sol = 19;
    public static int p20_1_Sol_Diese = 20;
    public static int p21_1_La = 21;
    public static int p22_1_La_Diese = 22;
    public static int p23_1_Si = 23;
    public static int p24_2_Do = 24;
    public static int p25_2_Do_Diese = 25;
    public static int p26_2_Re = 26;
    public static int p27_2_Re_Diese = 27;
    public static int p28_2_Mi = 28;
    public static int p29_2_Fa = 29;
    public static int p30_2_Fa_Diese = 30;
    public static int p31_2_Sol = 31;
    public static int p32_2_Sol_Diese = 32;
    public static int p33_2_La = 33;
    public static int p34_2_La_Diese = 34;
    public static int p35_2_Si = 35;
    public static int p36_3_Do = 36;
    public static int p37_3_Do_Diese = 37;
    public static int p38_3_Re = 38;
    public static int p39_3_Re_Diese = 39;
    public static int p40_3_Mi = 40;
    public static int p41_3_Fa = 41;
    public static int p42_3_Fa_Diese = 42;
    public static int p43_3_Sol = 43;
    public static int p44_3_Sol_Diese = 44;
    public static int p45_3_La = 45;
    public static int p46_3_La_Diese = 46;
    public static int p47_3_Si = 47;
    public static int p48_4_Do = 48;
    public static int p49_4_Do_Diese = 49;
    public static int p50_4_Re = 50;
    public static int p51_4_Re_Diese = 51;
    public static int p52_4_Mi = 52;
    public static int p53_4_Fa = 53;
    public static int p54_4_Fa_Diese = 54;
    public static int p55_4_Sol = 55;
    public static int p56_4_Sol_Diese = 56;
    public static int p57_4_La = 57;
    public static int p58_4_La_Diese = 58;
    public static int p59_4_Si = 59;
    public static int p60_5_Do = 60;
    public static int p61_5_Do_Diese = 61;
    public static int p62_5_Re = 62;
    public static int p63_5_Re_Diese = 63;
    public static int p64_5_Mi = 64;
    public static int p65_5_Fa = 65;
    public static int p66_5_Fa_Diese = 66;
    public static int p67_5_Sol = 67;
    public static int p68_5_Sol_Diese = 68;
    public static int p69_5_La = 69;
    public static int p70_5_La_Diese = 70;
    public static int p71_5_Si = 71;
    public static int p72_6_Do = 72;
    public static int p73_6_Do_Diese = 73;
    public static int p74_6_Re = 74;
    public static int p75_6_Re_Diese = 75;
    public static int p76_6_Mi = 76;
    public static int p77_6_Fa = 77;
    public static int p78_6_Fa_Diese = 78;
    public static int p79_6_Sol = 79;
    public static int p80_6_Sol_Diese = 80;
    public static int p81_6_La = 81;
    public static int p82_6_La_Diese = 82;
    public static int p83_6_Si = 83;
    public static int p84_7_Do = 84;
    public static int p85_7_Do_Diese = 85;
    public static int p86_7_Re = 86;
    public static int p87_7_Re_Diese = 87;
    public static int p88_7_Mi = 88;
    public static int p89_7_Fa = 89;
    public static int p90_7_Fa_Diese = 90;
    public static int p91_7_Sol = 91;
    public static int p92_7_Sol_Diese = 92;
    public static int p93_7_La = 93;
    public static int p94_7_La_Diese = 94;
    public static int p95_7_Si = 95;
    public static int p96_8_Do = 96;
    public static int p97_8_Do_Diese = 97;
    public static int p98_8_Re = 98;
    public static int p99_8_Re_Diese = 99;
    public static int p100_8_Mi = 100;
    public static int p101_8_Fa = 101;
    public static int p102_8_Fa_Diese = 102;
    public static int p103_8_Sol = 103;
    public static int p104_8_Sol_Diese = 104;
    public static int p105_8_La = 105;
    public static int p106_8_La_Diese = 106;
    public static int p107_8_Si = 107;
    public static int p108_9_Do = 108;
    public static int p109_9_Do_Diese = 109;
    public static int p110_9_Re = 110;
    public static int p111_9_Re_Diese = 111;
    public static int p112_9_Mi = 112;
    public static int p113_9_Fa = 113;
    public static int p114_9_Fa_Diese = 114;
    public static int p115_9_Sol = 115;
    public static int p116_9_Sol_Diese = 116;
    public static int p117_9_La = 117;
    public static int p118_9_La_Diese = 118;
    public static int p119_9_Si = 119;
    public static int p120_10_Do = 120;
    public static int p121_10_Do_Diese = 121;
    public static int p122_10_Re = 122;
    public static int p123_10_Re_Diese = 123;
    public static int p124_10_Mi = 124;
    public static int p125_10_Fa = 125;
    public static int p126_10_Fa_Diese = 126;
    public static int p127_10_Sol = 127;
}
