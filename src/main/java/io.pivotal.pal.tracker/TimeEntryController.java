package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TimeEntryController {
    private TimeEntryRepository inMemoryTimeEntryRepository;


    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
    this.inMemoryTimeEntryRepository = timeEntryRepository;
    }
    @PostMapping("/time-entries")
    public ResponseEntity create(@RequestBody TimeEntry timeEntryToCreate) {
        return new ResponseEntity<TimeEntry>( inMemoryTimeEntryRepository.create(timeEntryToCreate), HttpStatus.CREATED);

    }
    @GetMapping("/time-entries/{timeEntryId}")
    public ResponseEntity<TimeEntry> read(@PathVariable long timeEntryId) {
        TimeEntry timeEntry= inMemoryTimeEntryRepository.find(timeEntryId);
        if(timeEntry != null) {

            return new ResponseEntity<TimeEntry>(timeEntry, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<TimeEntry>(timeEntry, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/time-entries")
    public ResponseEntity<List<TimeEntry>> list() {
        return new ResponseEntity<List<TimeEntry>>( inMemoryTimeEntryRepository.list(), HttpStatus.OK);
    }  ;

    @PutMapping("/time-entries/{timeEntryId}")
    public ResponseEntity update(@PathVariable long timeEntryId, @RequestBody TimeEntry expected) {
        TimeEntry timeEntry= inMemoryTimeEntryRepository.update(timeEntryId,expected);

        if(timeEntry !=null) {
            return new ResponseEntity<TimeEntry>(timeEntry, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<TimeEntry>(timeEntry, HttpStatus.NOT_FOUND);
        }
        }
    @DeleteMapping("/time-entries/{timeEntryId}")
    public ResponseEntity delete(@PathVariable long timeEntryId) {
        inMemoryTimeEntryRepository.delete(timeEntryId);
        return new ResponseEntity<>(true, HttpStatus.NO_CONTENT);
    }
}
