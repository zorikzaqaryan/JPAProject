package am.gitc.jpa.manager;

import am.gitc.jpa.data.Lecture;

import java.util.List;

/**
 * Created by Zorik Zaqaryan on 07.11.2015.
 */
public interface ILectureManager {

    public void addLecture(Lecture lecture);

    public List<Lecture> getLecturesByName(String name);
}
