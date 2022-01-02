package bricks.trait.subject;

import bricks.trait.Push;
import bricks.trait.Trait;
import bricks.trait.Traits;
import suite.suite.SolidSubject;
import suite.suite.Subject;

public class SubjectPush extends SolidSubject {

    private int openedActions;
    private final Trait<Long> pusher;

    public SubjectPush() {
        super();
        pusher = Traits.set();
        openedActions = 0;
    }

    public SubjectPush(Subject subject) {
        super(subject);
        pusher = Traits.set();
        openedActions = 0;
    }

    public Push<Long> changes() {
        return pusher;
    }

    @Override
    protected Subject materialize(Object element) {
        ++openedActions;
        super.materialize(element);
        if(--openedActions < 1) pusher.set(System.currentTimeMillis());
        return this;
    }

    @Override
    protected Subject materialize() {
        ++openedActions;
        super.materialize();
        if(--openedActions < 1) pusher.set(System.currentTimeMillis());
        return this;
    }

    @Override
    public Subject set(Object element) {
        ++openedActions;
        super.set(element);
        if(--openedActions < 1) pusher.set(System.currentTimeMillis());
        return this;
    }

    @Override
    public Subject aimedSet(Object aim, Object element) {
        ++openedActions;
        super.aimedSet(aim, element);
        if(--openedActions < 1) pusher.set(System.currentTimeMillis());
        return this;
    }

    @Override
    public Subject inset(Object in, Subject $set) {
        ++openedActions;
        super.inset(in, $set);
        if(--openedActions < 1) pusher.set(System.currentTimeMillis());
        return this;
    }

    @Override
    public Subject aimedInset(Object aim, Object in, Subject $set) {
        ++openedActions;
        super.aimedInset(aim, in, $set);
        if(--openedActions < 1) pusher.set(System.currentTimeMillis());
        return this;
    }

    @Override
    public Subject swap(Object o1, Object o2) {
        ++openedActions;
        super.swap(o1, o2);
        if(--openedActions < 1) pusher.set(System.currentTimeMillis());
        return this;
    }

    @Override
    public Subject unset() {
        ++openedActions;
        super.unset();
        if(--openedActions < 1) pusher.set(System.currentTimeMillis());
        return this;
    }

    @Override
    public Subject unset(Object element) {
        ++openedActions;
        super.unset(element);
        if(--openedActions < 1) pusher.set(System.currentTimeMillis());
        return this;
    }

    @Override
    public Subject alter(Iterable<? extends Subject> iterable) {
        ++openedActions;
        super.alter(iterable);
        if(--openedActions < 1) pusher.set(System.currentTimeMillis());
        return this;
    }

    @Override
    public Subject aimedAlter(Object sequent, Iterable<? extends Subject> iterable) {
        ++openedActions;
        super.aimedAlter(sequent, iterable);
        if(--openedActions < 1) pusher.set(System.currentTimeMillis());
        return this;
    }

    @Override
    public Subject takeEntire(Iterable<?> iterable) {
        ++openedActions;
        var taken = super.takeEntire(iterable);
        if(--openedActions < 1) pusher.set(System.currentTimeMillis());
        return taken;
    }

    @Override
    public Subject put(Object e1, Object... en) {
        ++openedActions;
        super.put(e1, en);
        if(--openedActions < 1) pusher.set(System.currentTimeMillis());
        return this;
    }

    @Override
    public Subject add(Object element) {
        ++openedActions;
        super.add(element);
        if(--openedActions < 1) pusher.set(System.currentTimeMillis());
        return this;
    }

    @Override
    public Subject inset(Subject $set) {
        ++openedActions;
        super.inset($set);
        if(--openedActions < 1) pusher.set(System.currentTimeMillis());
        return this;
    }

    @Override
    public Subject aimedPut(Object aim, Object key, Object... en) {
        ++openedActions;
        super.aimedPut(aim, key, en);
        if(--openedActions < 1) pusher.set(System.currentTimeMillis());
        return this;
    }

    @Override
    public Subject aimedAdd(Object aim, Object element) {
        ++openedActions;
        super.aimedAdd(aim, element);
        if(--openedActions < 1) pusher.set(System.currentTimeMillis());
        return this;
    }

    @Override
    public Subject aimedInset(Object aim, Subject $set) {
        ++openedActions;
        super.aimedInset(aim, $set);
        if(--openedActions < 1) pusher.set(System.currentTimeMillis());
        return this;
    }

    @Override
    public Subject reset(Object element) {
        ++openedActions;
        super.reset(element);
        if(--openedActions < 1) pusher.set(System.currentTimeMillis());
        return this;
    }

    @Override
    public Subject merge(Subject $tree) {
        ++openedActions;
        super.merge($tree);
        if(--openedActions < 1) pusher.set(System.currentTimeMillis());
        return this;
    }

    @Override
    public Subject set(Object... elements) {
        ++openedActions;
        super.set(elements);
        if(--openedActions < 1) pusher.set(System.currentTimeMillis());
        return this;
    }

    @Override
    public Subject setEntire(Iterable<?> iterable) {
        ++openedActions;
        super.setEntire(iterable);
        if(--openedActions < 1) pusher.set(System.currentTimeMillis());
        return this;
    }

    @Override
    public Subject add(Object... elements) {
        ++openedActions;
        super.add(elements);
        if(--openedActions < 1) pusher.set(System.currentTimeMillis());
        return this;
    }

    @Override
    public Subject addEntire(Iterable<?> iterable) {
        ++openedActions;
        super.addEntire(iterable);
        if(--openedActions < 1) pusher.set(System.currentTimeMillis());
        return this;
    }

    @Override
    public Subject unset(Object... elements) {
        ++openedActions;
        super.unset(elements);
        if(--openedActions < 1) pusher.set(System.currentTimeMillis());
        return this;
    }

    @Override
    public Subject unsetEntire(Iterable<?> iterable) {
        ++openedActions;
        super.unsetEntire(iterable);
        if(--openedActions < 1) pusher.set(System.currentTimeMillis());
        return this;
    }

    @Override
    public Subject take(Object... elements) {
        ++openedActions;
        super.take(elements);
        if(--openedActions < 1) pusher.set(System.currentTimeMillis());
        return this;
    }

    @Override
    public Subject merge(Subject... subjects) {
        ++openedActions;
        super.merge(subjects);
        if(--openedActions < 1) pusher.set(System.currentTimeMillis());
        return this;
    }

    @Override
    public Subject mergeEntire(Iterable<? extends Subject> iterable) {
        ++openedActions;
        super.mergeEntire(iterable);
        if(--openedActions < 1) pusher.set(System.currentTimeMillis());
        return this;
    }
}
