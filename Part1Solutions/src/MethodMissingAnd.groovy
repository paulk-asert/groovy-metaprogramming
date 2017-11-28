class Person {
    String first, last
}

class People {
    List<Person> folks = [
            new Person(first: 'John', last: 'Smith'),
            new Person(first: 'Mary', last: 'Smith'),
            new Person(first: 'John', last: 'Jones')]

    def methodMissing(String name, args) {
        assert name.startsWith('findAllBy')
        def keysRaw = name - 'findAllBy'
        process(keysRaw, 'And', args)
    }

    private process(String keysRaw, String splitOp, args) {
        def keys = keysRaw.split(splitOp)
        assert keys.size() == args.size()
        folks.findAll { p ->
            keys.indices.every { idx ->
                p."${keys[idx].uncapitalize()}" == args[idx]
            }
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
