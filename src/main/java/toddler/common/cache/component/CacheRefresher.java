package toddler.common.cache.component;

import toddler.common.util.Lists;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by jun.li
 */
public class CacheRefresher implements Runnable {

    private ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();


    private List<CacheComponent> caches = Lists.newArrayList();


    public void refresh() {
        // todo catch exception
        caches.stream().forEach(CacheComponent::refresh);
    }

    @Override
    public void run() {
        refresh();
    }

    public void scheduling(long initialDelay, long delay) {
        executor.scheduleWithFixedDelay(this, initialDelay, delay, TimeUnit.SECONDS);
    }
}
