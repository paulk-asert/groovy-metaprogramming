package p2
// extend this example to allow either 'And' and 'Or' conjunctions
// but not both together in the findAllBy methods

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

def all = p.findAllByFirstOrLast('John', 'Smith')
assert all.size() == 3
