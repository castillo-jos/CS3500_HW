
                           Music Creator Model

  What is it?
  -----------

  The Music Creator Model is a collection of classes, interfaces, enums,
  and Junit test files that can organize notes, beats, and octaves into 
  a neat, readable format, intended to be a place holder for future projects.

  Files Included
  --------------

  Besides this README.txt file, within this package you'll find five folders:
	1. java doc
	2. src
	3. .idea
	4. test
	5. out

  Documentation
  -------------

  Documentation can be found by going into the 'java doc' folder and clicking on the
  "index" html folder.

  src
  ---
  
  Four folders are included in this folder:
	1. MusicCreatorOperations - An interface that lists all methods needed to add, 
	   store, delete, remove, and manipulate classes that store values for pitches,
	   octaves, beat, and note duration. 
	   Methods include:
	 	- startCreator: if the range of notes are known immediately, the model
		  can initialize the proper list of notes right away. 
		- addNote: adds a Note Object to a list
		- removesFirstNote: Removes the first note from list
		- removeLastNote: Removes last note from list
		- addBeat: In the selected Note, a beat w/ duration is add.
		- removeBeat: In the selected Note, a beat is removed.
		- showSheetMusic: Formats all notes and beats in a neat, readable fashion.
	2. MusicCreatorModel - a implementation of above interface.
	3. Note - a class that stores pitches, octave values, and a list of beats. 
	   Methods include:
		- 2 constructors, one that initializes a Note object with parameters "Pitch"
		  and int "octave", and one that can take a int (noteID) and derive a pitch 
		  and octave value. 
		- addBeat & removeBeat: functions that manage the list of beats stored in 
		  each Note object.
	4. PitchNames - Enums of consisting of 13 note values.

  test
  ----
	1. MusicCreatorModelTest: testing methods of MusicCreatorModel.
	2. NoteTests: testing methods of Note.


  Contact
  -------

    hmu at castillo.jos@husky.neu.edu