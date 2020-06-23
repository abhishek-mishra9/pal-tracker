package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    HashMap<Long,TimeEntry> inMemoryData= new HashMap();
    Long counter= 0l;
    @Override
    public TimeEntry create(TimeEntry timeEntry) {
        timeEntry.setId(++counter);
        inMemoryData.put(timeEntry.getId(),timeEntry);
        return inMemoryData.get(timeEntry.getId());
    }

    @Override
    public TimeEntry find(Long id) {

        return   inMemoryData.get(id);
    }

    @Override
    public List<TimeEntry> list() {
          List<TimeEntry> timeEntryList = new ArrayList<>(inMemoryData.values());

        return timeEntryList;
    }

    @Override
    public TimeEntry update(Long id, TimeEntry timeEntry) {

        if(inMemoryData.containsKey(id)){
            timeEntry.setId(id);
            inMemoryData.put(id,timeEntry);
        }

        return inMemoryData.get(id);
    }

    @Override
    public void delete(Long id) {

        inMemoryData.remove(id);

    }
}
