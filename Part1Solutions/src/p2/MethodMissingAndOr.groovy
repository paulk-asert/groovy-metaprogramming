package p2

class Person {
    String first, last
    int age
}

class People {
    List<Person> folks = [
            new Person(first: 'John', last: 'Smith'),
            new Person(first: 'Mary', last: 'Smith'),
            new Person(first: 'John', last: 'Jones')]

    def methodMissing(String name, args) {
        assert name.startsWith('findAllBy')
        def keysRaw = name - 'findAllBy'
        if (keysRaw.contains('Or') && !keysRaw.contains('And'))
            process(keysRaw, 'Or', 'any', args)
        else if (!keysRaw.contains('Or'))
            process(keysRaw, 'And', 'every', args)
    }

    private process(String keysRaw, String splitBy, String compOp, args) {
        def keys = keysRaw.split(splitBy)
        assert keys.size() == args.size()
        folks.findAll { p ->
            keys.indices."$compOp" { idx ->
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

def all = p.findAllByFirstOrLast('John', 'Smith')
assert all.size() == 3
