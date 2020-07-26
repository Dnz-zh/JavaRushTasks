package com.javarush.task.task37.task3708.retrievers;

import com.javarush.task.task37.task3708.cache.LRUCache;
import com.javarush.task.task37.task3708.storage.Storage;

public class CachingProxyRetriever implements Retriever {

    private OriginalRetriever originalRetriever;
    private LRUCache lruCache = new LRUCache(10);

    public CachingProxyRetriever(Storage storage) {
        originalRetriever = new OriginalRetriever(storage);

    }

    @Override
    public Object retrieve(long id) {
        if (lruCache.find(id) == null) {
            Object result = originalRetriever.retrieve(id);
            lruCache.set(id, result);
            return result;
        }
        return lruCache.find(id);
    }
}
