package com.rlamarques.roomwordsample;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

class WordRepository {

    private WordDao mWordDao;
    private LiveData<List<Word>> mAllWords;

    WordRepository(Application application) {
        WordRoomDatabase db = WordRoomDatabase.getDatabase(application);
        mWordDao = db.wordDao();
        mAllWords = mWordDao.getAlphabetizedWords();
    }

    LiveData<List<Word>> getAllWords() {
        return mAllWords;
    }

    void insert(Word word) {
        WordRoomDatabase.databaseWriterExecutor.execute(() -> {
            mWordDao.insert(word);
        });
    }
}
