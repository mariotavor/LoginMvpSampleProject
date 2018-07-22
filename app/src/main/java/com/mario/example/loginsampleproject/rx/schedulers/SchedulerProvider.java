package com.mario.example.loginsampleproject.rx.schedulers;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.mario.example.loginsampleproject.rx.schedulers.ISchedulerProvider;

import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Provides different types of schedulers.
 */
public class SchedulerProvider implements ISchedulerProvider
{

    @Nullable
    private static SchedulerProvider INSTANCE;

    // Prevent direct instantiation.
    public SchedulerProvider() {
    }

    public static SchedulerProvider getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SchedulerProvider();
        }
        return INSTANCE;
    }

    @Override
    @NonNull
    public Scheduler computation() {
        return Schedulers.computation();
    }

    @Override
    @NonNull
    public Scheduler ui() {
        return AndroidSchedulers.mainThread();
    }

    @NonNull
    @Override
    public Scheduler io()
    {
        return Schedulers.io();
    }
}
