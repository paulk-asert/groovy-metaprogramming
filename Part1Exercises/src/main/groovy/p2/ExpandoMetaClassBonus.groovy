package p2

class Committer {
    String firstName
    String lastName
}

// add a constructor and static make method
// to ease object creation using EMC syntax

def daniel = new Committer('Daniel', 'Sun')
assert daniel.fullName() == 'Daniel Sun'

def john = Committer.make('John', 'Wagenleitner')
assert john.fullName() == 'John Wagenleitner'
