package com.wouterv.twatter.Batch;

import java.io.Serializable;

public final class Checkpoint implements Serializable {

    private long eventCount;

    public Checkpoint() {
        eventCount = 0;
    }

    public final void eventHappened() {
        ++eventCount;
    }

    public final long getCount() {
        return eventCount;
    }
}
