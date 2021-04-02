package bricks.input;

import suite.suite.Subject;

import static suite.suite.$uite.$;

public class Story {

    private int limit;
    private Subject $past;
    private Subject $future;

    public Story(int limit) {
        $past = $();
        $future = $();
        this.limit = limit;
    }

    public void push(UserAction action) {
        if($past.size() >= limit) {
            $past.unset($past.first().raw());
        }
        $future.unset();
        $past.add(action);
    }

    public Subject pop() {
        return $past.take($past.last().raw());
    }

    public boolean back() {
        var $action = pop();
        if($action.absent()) return false;
        UserAction action = $action.in().asExpected();
        action.back();
        $future.add(action);
        return true;
    }

    public boolean front() {
        var $action = $future.take($future.last().raw());
        if($action.absent()) return false;
        UserAction action = $action.in().asExpected();
        action.front();
        $past.add(action);
        return true;
    }

    public boolean hasPast() {
        return $past.present();
    }

    public boolean hasFuture() {
        return $future.present();
    }
}
