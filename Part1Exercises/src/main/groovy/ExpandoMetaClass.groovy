class Committer {
    String firstName
    String lastName
}

// add a fullName() method using the ExpandoMetaClass syntax

def daniel = new Committer(firstName: 'Daniel', lastName: 'Sun')
assert daniel.fullName() == 'Daniel Sun'
