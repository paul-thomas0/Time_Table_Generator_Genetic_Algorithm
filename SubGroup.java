public class SubGroup {
    public int faculty_id = 0000;
    public int subject_id = 0000;
    public boolean isSubGroupSlotEmpty = true;

    public SubGroup() {

    }

    public SubGroup(int subject_id, int faculty_id) {
        this.faculty_id = faculty_id;
        this.subject_id = subject_id;
        this.isSubGroupSlotEmpty = false;
    }

    public int getFaculty_id() {
        return faculty_id;
    }

    public void setFaculty_id(int faculty_id) {
        this.faculty_id = faculty_id;
    }

    public int getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(int subject_id) {
        this.subject_id = subject_id;
    }

    public boolean isSubGroupSlotEmpty() {
        return isSubGroupSlotEmpty;
    }

    public void setSubGroupSlotEmpty(boolean subGroupSlotEmpty) {
        isSubGroupSlotEmpty = subGroupSlotEmpty;
    }




}
