package model.constants;

public enum Status {
    NEW, DONE, IN_PROGRESS;

    public static Status getFromString(String string) {
        if (NEW.toString().equals(string)) {
            return NEW;
        } else if (DONE.toString().equals(string)) {
            return DONE;
        } else if (IN_PROGRESS.toString().equals(string)) {
            return IN_PROGRESS;
        } else {
            System.out.println("Unsupported status type");
            return null;
        }
    }
}
