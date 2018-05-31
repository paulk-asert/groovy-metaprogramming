import groovy.transform.Immutable
abstract class Tree {}
@Immutable class Branch extends Tree { Tree left, right }
@Immutable class Branch3 extends Tree { Tree left, right, middle }
@Immutable class Leaf extends Tree { int val }

@Newify([Branch, Leaf])
def t = Branch(Leaf(1), Branch(Branch(Leaf(2), Leaf(3)), Leaf(4)))
assert t.toString() == 'Branch(Leaf(1), Branch(Branch(Leaf(2), Leaf(3)), Leaf(4)))'

@Newify(pattern='[BL].*')
def t = Branch(Leaf(1), Branch(Branch3(Leaf(2), Leaf(3), Leaf(4)), Leaf(5)))
assert t.toString() == 'Branch(Leaf(1), Branch(Branch(Leaf(2), Leaf(3)), Leaf(4)))'
