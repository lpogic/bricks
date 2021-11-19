package bricks.var.subject;

import suite.suite.Subject;

public interface SubjectPushChange {

    record Set(Object o) implements SubjectPushChange{}
    record Swap(Object o1, Object o2) implements SubjectPushChange{}
    record TotalUnset() implements SubjectPushChange{}
    record Unset(Object o) implements SubjectPushChange{}
    record Alter(Iterable<? extends Subject> iterable) implements SubjectPushChange{}
}
