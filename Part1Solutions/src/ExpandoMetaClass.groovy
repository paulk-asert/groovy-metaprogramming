class Committer {
    String firstName
    String lastName
}

Committer.metaClass.fullName = { ->
    "$delegate.firstName $delegate.lastName"
}

def daniel = new Committer(firstName: 'Daniel', lastName: 'Sun')
assert daniel.fullName() == 'Daniel Sun'
