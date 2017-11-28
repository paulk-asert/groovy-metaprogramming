// extend this example to allow 'And' conjunctions in
// the findAllBy methods

// hints:
// split('And') may prove useful
// indices can be a useful property on a list

class Person {
    String first, last
}

class People {
    List<Person> folks = [
            new Person(first: 'John', last: 'Smith'),
            new Person(first: 'Mary', last: 'Smith'),
            new Person(first: 'John', last: 'Jones'),
    ]
    def methodMissing(String name, args) {
        assert name.startsWith('findAllBy')
        assert args.size() == 1
        folks.findAll { p ->
            p."${(name - 'findAllBy').uncapitalize()}" == args[0]
        }
    }
}

def p = new People()
def johns = p.findAllByFirst('John').last
assert johns == ['Smith', 'Jones']
def smiths = p.findAllByLast('Smith').first
assert smiths == ['John', 'Mary']

def fullname = { "$it.first $it.last" }
def ms = p.findAllByFirstAndLast('Mary',
                                 'Smith').collect(fullname)
assert ms == ['Mary Smith']
def jj = p.findAllByLastAndFirst('Jones',
                                 'John').collect(fullname)
assert jj == ['John Jones']
