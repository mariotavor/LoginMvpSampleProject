package com.mario.example.loginsampleproject.rx.schedulers;

import android.support.annotation.NonNull;

import com.mario.example.loginsampleproject.rx.schedulers.ISchedulerProvider;

import rx.Scheduler;
import rx.schedulers.Schedulers;

/**
 * Implementation of the {@link ISchedulerProvider} making all {@link Scheduler}s immediate.
 */
public class ImmediateSchedulerProvider implements ISchedulerProvider
{

    @NonNull
    @Override
    public Scheduler computation() {
        return Schedulers.immediate();
    }

    @NonNull
    @Override
    public Scheduler ui() {
        return Schedulers.immediate();
    }

    @NonNull
    @Override
    public Scheduler io()
    {
        return Schedulers.immediate();
    }
}
