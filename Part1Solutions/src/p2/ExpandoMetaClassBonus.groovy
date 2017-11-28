package p2

class Committer {
    String firstName
    String lastName
}

Committer.metaClass.fullName = { ->
    "$delegate.firstName $delegate.lastName"
}
Committer.metaClass.constructor = { String f, String l ->
    new Committer(firstName: f, lastName: l)
}
Committer.metaClass.static.make = { String f, String l ->
    new Committer(firstName: f, lastName: l)
}

def daniel = new Committer('Daniel', 'Sun')
assert daniel.fullName() == 'Daniel Sun'

def john = Committer.make('John', 'Wagenleitner')
assert john.fullName() == 'John Wagenleitner'
