package com.aqua.music.example.easymidi;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import com.aqua.music.example.easymidi.AbsractPlayable.Drum;
import com.aqua.music.example.easymidi.AbsractPlayable.Note;
import com.aqua.music.example.easymidi.Chord.ChordsList;

public class Demo extends JFrame {
	private final int bassVoice = Playable.i33_Electric_Bass_finger;

	private final Drum hat = new Drum(4, Playable.d42_Closed_Hi_Hat, 64);
	private final Drum snare = new Drum(4, Playable.d38_Acoustic_Snare);
	private final Drum bass = new Drum(4, Playable.d35_Acoustic_Bass_Drum);

	private final Note bullet = new Note(4, Playable.p96_8_Do, Playable.i127_Gunshot);
	private final Note backgroundDrumNote = new Note(8, Playable.p28_2_Mi, bassVoice);

	ChordsList backgroundDrumChordList = new ChordsList().chord(new Chord(8).addDrum(hat).addDrum(bass).addNote(backgroundDrumNote))
			.chord(new Chord(8).addDrum(hat).addNote(backgroundDrumNote))
			.chord(new Chord(8).addDrum(hat).addDrum(snare).addNote(backgroundDrumNote))
			.chord(new Chord(8).addDrum(hat).addNote(backgroundDrumNote))
			.chord(new Chord(8).addDrum(hat).addDrum(bass).addNote(backgroundDrumNote))
			.chord(new Chord(8).addDrum(hat).addDrum(bass).addNote(backgroundDrumNote))
			.chord(new Chord(8).addDrum(hat).addDrum(snare).addNote(8, Playable.p34_2_La_Diese, bassVoice))
			.chord(new Chord(8).addDrum(hat).addNote(8, Playable.p35_2_Si, bassVoice));

	Ticker backgroundDrumsTicker = new Ticker(120, backgroundDrumChordList) {
		public void onTick(long count) {
			if (count == 0) {
				System.out.println("begin");
			}
		}
	};

	Ticker gunSerie = new Ticker(180, new ChordsList().chord(new Chord(8).addNote(bullet)).chord(new Chord(8).note(bullet))
			.chord(new Chord(8).note(bullet)).chord(new Chord(8).note(bullet)).chord(new Chord(8).note(bullet))) {
		@Override
		public void onFinish() {
			stop();
		}
	};

	JButton play = new JButton("Play");
	JButton stop = new JButton("Stop");
	JButton gun = new JButton("Gun");
	JButton tweet = new JButton("Tweet");
	JButton mgun = new JButton("Machine Gun");
	JButton clap = new JButton("Clap");

	public Demo() {
		Tools.initSynthesizer();
		backgroundDrumsTicker.restart();
		this.setSize(500, 150);
		this.setTitle("EasyMIDI test");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new FlowLayout());
		this.add(stop);
		this.add(play);
		this.add(gun);
		this.add(mgun);
		this.add(tweet);
		this.add(clap);
		stop.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				backgroundDrumsTicker.stop();
			}
		});
		play.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				backgroundDrumsTicker.restart();
			}
		});
		gun.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Tools.playNote(Playable.p93_7_La, Playable.i127_Gunshot, 127, 2000);
			}
		});
		mgun.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gunSerie.restart();
			}
		});
		tweet.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Tools.playNote(Playable.p69_5_La, Playable.i123_Bird_Tweet, 99, 3000);
			}
		});
		clap.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Tools.playDrum(Playable.d39_Hand_Clap, 127, 2000);
			}
		});
		this.setVisible(true);
	}

	public static void main(String[] args) {
		new Demo();
	}
}