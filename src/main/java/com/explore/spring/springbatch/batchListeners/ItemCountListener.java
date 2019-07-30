package com.explore.spring.springbatch.batchListeners;

import org.apache.log4j.Logger;
import org.springframework.batch.core.ChunkListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.stereotype.Component;

@Component
public class ItemCountListener implements ChunkListener {

    protected static final Logger LOG = Logger.getLogger(ItemCountListener.class);

    @Override
    public void beforeChunk(ChunkContext context) {
    }

    @Override
    public void afterChunk(ChunkContext context) {

        int count = context.getStepContext().getStepExecution().getReadCount();
        LOG.info("Chunk Count: " + count);
    }

    @Override
    public void afterChunkError(ChunkContext context) {
    }
}
