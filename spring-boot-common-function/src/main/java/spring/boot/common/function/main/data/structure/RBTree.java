package spring.boot.common.function.main.data.structure;

public class RBTree<T extends Comparable<T>> {
    private Node<T> root;
    private static final boolean RED = false;
    private static final boolean BLACK = true;

    public class Node<T extends Comparable<T>> {
        T key;
        Node<T> left;
        Node<T> right;
        Node<T> parent;
        boolean color;

        public Node(T key, boolean color, Node<T> parent, Node<T> left, Node<T> right) {
            this.key = key;
            this.color = color;
            this.parent = parent;
            this.left = left;
            this.right = right;
        }

        public T getKey() {
            return key;
        }
    }

    //********************基本属性定义--结束*********************************************//

    public RBTree() {
        root = null;
    }

    private Node<T> parentOf(Node<T> node) {
        return node == null ? null : node.parent;
    }

    private boolean colorOf(Node<T> node) {
        return node == null ? BLACK : node.color;
    }

    private boolean isRed(Node<T> node) {
        return ((node != null) && (node.color == RED)) ? true : false;
    }

    private boolean isBlack(Node<T> node) {
        return !isRed(node);
    }

    private void setBlack(Node<T> node) {
        if (node != null) {
            node.color = BLACK;
        }
    }

    private void setRed(Node<T> node) {
        if (node != null) {
            node.color = RED;
        }
    }

    private void setParent(Node<T> node, Node<T> parent) {
        if (node != null) {
            node.parent = parent;
        }
    }

    private void setColor(Node<T> node, boolean color) {
        if (node != null) {
            node.color = color;
        }
    }

    //********************基本方法定义--结束*********************************************//

    /**
     * 前序遍历-红黑树
     */
    public void preOrder() {
        preOrder(root);
    }

    private void preOrder(Node<T> tree) {
        if (tree != null) {
            System.out.print(tree.key + " ");
            preOrder(tree.left);
            preOrder(tree.right);
        }
    }

    /**
     * 中序遍历-红黑树
     */
    public void inOrder() {
        inOrder(root);
    }

    private void inOrder(Node<T> tree) {
        System.out.println("-");
        if (tree != null) {
            inOrder(tree.left);
            System.out.print(tree.key + " ");
            inOrder(tree.right);
        }
    }

    /**
     * 后序遍历-红黑树
     */
    public void postOrder() {
        postOrder(root);
    }

    private void postOrder(Node<T> tree) {
        if (tree != null) {
            postOrder(tree.left);
            postOrder(tree.right);
            System.out.print(tree.key + " ");
        }
    }


    /**
     * (递归实现)查找"红黑树x"中键值为key的节点
     */
    public Node<T> search(T key) {
        return search(root, key);
    }

    private Node<T> search(Node<T> x, T key) {
        if (x == null) {
            return x;
        }

        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            return search(x.left, key);
        } else if (cmp > 0) {
            return search(x.right, key);
        } else {
            return x;
        }
    }

    /**
     * (非递归实现)查找"红黑树x"中键值为key的节点
     */
    public Node<T> iterativeSearch(T key) {
        return iterativeSearch(root, key);
    }

    private Node<T> iterativeSearch(Node<T> x, T key) {
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp < 0) {
                x = x.left;
            } else if (cmp > 0) {
                x = x.right;
            } else {
                return x;
            }
        }
        return x;
    }


    /**
     * 查找最小结点：返回tree为根结点的红黑树的最小结点。
     */
    public T miniMum() {
        Node<T> p = miniMum(root);
        if (p != null) {
            return p.key;
        }
        return null;
    }

    private Node<T> miniMum(Node<T> tree) {
        if (tree == null) {
            return null;
        }
        while (tree.left != null) {
            tree = tree.left;
        }
        return tree;
    }

    /**
     * 查找最大结点：返回tree为根结点的红黑树的最大结点。
     */
    public T maxiMum() {
        Node<T> p = maxiMum(root);
        if (p != null) {
            return p.key;
        }
        return null;
    }

    private Node<T> maxiMum(Node<T> tree) {
        if (tree == null) {
            return null;
        }
        while (tree.right != null) {
            tree = tree.right;
        }
        return tree;
    }

    /**
     * 找结点(x)的后继结点,即查找数据值大于该结点的"最小结点"。
     */
    public Node<T> successor(Node<T> x) {
        if (x == null) {
            return x;
        }
        // 如果x存在右孩子，则x的后继结点为以其右孩子为根的子树的最小结点。
        if (x.right != null) {
            return miniMum(x.right);
        }
        // 如果x没有右孩子。则x有以下两种可能：
        // (01) x是一个左孩子，则x的后继结点为它的父结点。
        // (02) x是一个右孩子，则while循环往上查询，直到某个节点是其父节点的左孩子。
        Node<T> y = x.parent;
        while ((y != null) && (x == y.right)) {
            x = y;
            y = y.parent;
        }
        return y;
    }

    /**
     * 找结点(x)的前驱结点。即查找"红黑树中数据值小于该结点"的"最大结点"。
     */
    public Node<T> predecessor(Node<T> x) {
        if (x == null) {
            return null;
        }
        // 如果x存在左孩子，则"x的前驱结点"为 "以其左孩子为根的子树的最大结点"。
        if (x.left != null) {
            return maxiMum(x.left);
        }
        // 如果x没有左孩子。则x有以下两种可能：
        // (01) x是"一个右孩子"，则"x的前驱结点"为 "它的父结点"。
        // (01) x是"一个左孩子"，则查找"x的最低的父结点，并且该父结点要具有右孩子"，找到的这个"最低的父结点"就是"x的前驱结点"。
        Node<T> y = x.parent;
        while ((y != null) && (x == y.left)) {
            x = y;
            y = y.parent;
        }
        return y;
    }

    /**
     * 对红黑树的节点(x)进行左旋转
     *          px                              px
     *         /                               /
     *        x                               y
     *      /  \      --(左旋)-.-           / \          --#
     *    lx   y                          x  ry
     *       / \                         / \
     *    ly   ry                      lx ly
     */
    private void leftRotate(Node<T> x) {
        Node<T> y = x.right;
        x.right = y.left;
        if (y.left != null) {
            y.left.parent = x;
        }
        y.parent = x.parent;

        if (x.parent == null) {
            this.root = y;
        } else {
            if (x.parent.left == x) {
                x.parent.left = y;
            }else {
                x.parent.right = y;
            }
        }
        y.left = x;
        x.parent = y;
    }

    /**
     * 对红黑树的节点(y)进行右旋转
     * 右旋示意图(对节点y进行左旋)：
     *        py                                 py
     *       /                                  /
     *      y                                 x
     *    /  \      --(右旋)-.              /  \
     *   x   ry                           lx   y
     * / \                               / \
     * lx  rx                           rx  ry
     */
    private void rightRotate(Node<T> y) {
        Node<T> x = y.left;
        y.left = x.right;
        if (x.right != null){
            x.right.parent = y;
        }
        x.parent = y.parent;

        if (y.parent == null) {
            this.root = x;
        } else {
            if (y == y.parent.right) {
                y.parent.right = x;
            }else{
                y.parent.left = x;
            }
        }
        x.right = y;
        y.parent = x;
    }

    /**
     * 在向红黑树中插入节点之后(失去平衡)，再调用该函数，重新塑造成一颗红黑树。
     * 参数说明：node 插入的结点
     */
    private void insertFixUp(Node<T> node) {
        Node<T> parent, gparent;
        while (((parent = parentOf(node)) != null) && isRed(parent)) {
            gparent = parentOf(parent);
            if (parent == gparent.left) {
                // Case 1条件：叔叔节点是红色
                Node<T> uncle = gparent.right;
                if ((uncle != null) && isRed(uncle)) {
                    setBlack(uncle);
                    setBlack(parent);
                    setRed(gparent);
                    node = gparent;
                    continue;
                }
                // Case 2条件：叔叔是黑色，且当前节点是右孩子
                if (parent.right == node) {
                    Node<T> tmp;
                    leftRotate(parent);
                    tmp = parent;
                    parent = node;
                    node = tmp;
                }
                // Case 3条件：叔叔是黑色，且当前节点是左孩子。
                setBlack(parent);
                setRed(gparent);
                rightRotate(gparent);
            } else {
                //若“z的父节点”是“z的祖父节点的右孩子”
                // Case 1条件：叔叔节点是红色
                Node<T> uncle = gparent.left;
                if ((uncle != null) && isRed(uncle)) {
                    setBlack(uncle);
                    setBlack(parent);
                    setRed(gparent);
                    node = gparent;
                    continue;
                }
                // Case 2条件：叔叔是黑色，且当前节点是左孩子
                if (parent.left == node) {
                    Node<T> tmp;
                    rightRotate(parent);
                    tmp = parent;
                    parent = node;
                    node = tmp;
                }
                // Case 3条件：叔叔是黑色，且当前节点是右孩子。
                setBlack(parent);
                setRed(gparent);
                leftRotate(gparent);
            }
        }
        // 将根节点设为黑色
        setBlack(this.root);
    }

    /**
     * 将结点插入到红黑树中
     */
    private void insert(Node<T> node) {
        int cmp;
        Node<T> y = null;
        Node<T> x = this.root;
        // 1. 将红黑树当作一颗二叉查找树，将节点添加到二叉查找树中。
        while (x != null) {
            y = x;
            cmp = node.key.compareTo(x.key);
            if (cmp < 0) {
                x = x.left;
            } else {
                x = x.right;
            }
        }

        node.parent = y;
        if (y != null) {
            cmp = node.key.compareTo(y.key);
            if (cmp < 0) {
                y.left = node;
            } else {
                y.right = node;
            }
        } else {
            this.root = node;
        }
        // 2. 设置节点的颜色为红色
        node.color = RED;
        // 3. 将它重新修正为一颗二叉查找树
        insertFixUp(node);
    }

    /**
     * 新建结点(key)，并将其插入到红黑树中
     */
    public void insert(T key) {
        Node<T> node = new Node<T>(key, BLACK, null, null, null);
        if (node != null) {
            insert(node);
        }
    }

    /**
     * 红黑树删除修正函数：在从红黑树中删除插入节点之后(红黑树失去平衡)，再调用该函数，将它重新塑造成一颗红黑树。
     * 参数说明：node 待修正的节点
     */
    private void removeFixUp(Node<T> node, Node<T> parent) {
        Node<T> other;
        while ((node == null || isBlack(node)) && (node != this.root)) {
            if (parent.left == node) {
                other = parent.right;
                if (isRed(other)) {
                    // Case 1: x的兄弟w是红色的
                    setBlack(other);
                    setRed(parent);
                    leftRotate(parent);
                    other = parent.right;
                }

                if ((other.left == null || isBlack(other.left)) && (other.right == null || isBlack(other.right))) {
                    // Case 2: x的兄弟w是黑色，且w的俩个孩子也都是黑色的
                    setRed(other);
                    node = parent;
                    parent = parentOf(node);
                } else {

                    if (other.right == null || isBlack(other.right)) {
                        // Case 3: x的兄弟w是黑色的，并且w的左孩子是红色，右孩子为黑色。
                        setBlack(other.left);
                        setRed(other);
                        rightRotate(other);
                        other = parent.right;
                    }
                    // Case 4: x的兄弟w是黑色的；并且w的右孩子是红色的，左孩子任意颜色。
                    setColor(other, colorOf(parent));
                    setBlack(parent);
                    setBlack(other.right);
                    leftRotate(parent);
                    node = this.root;
                    break;
                }
            } else {

                other = parent.left;
                if (isRed(other)) {
                    // Case 1: x的兄弟w是红色的
                    setBlack(other);
                    setRed(parent);
                    rightRotate(parent);
                    other = parent.left;
                }

                if ((other.left == null || isBlack(other.left)) &&
                        (other.right == null || isBlack(other.right))) {
                    // Case 2: x的兄弟w是黑色，且w的俩个孩子也都是黑色的
                    setRed(other);
                    node = parent;
                    parent = parentOf(node);
                } else {

                    if (other.left == null || isBlack(other.left)) {
                        // Case 3: x的兄弟w是黑色的，并且w的左孩子是红色，右孩子为黑色。
                        setBlack(other.right);
                        setRed(other);
                        leftRotate(other);
                        other = parent.left;
                    }

                    // Case 4: x的兄弟w是黑色的；并且w的右孩子是红色的，左孩子任意颜色。
                    setColor(other, colorOf(parent));
                    setBlack(parent);
                    setBlack(other.left);
                    rightRotate(parent);
                    node = this.root;
                    break;
                }
            }
        }

        if (node != null)
            setBlack(node);
    }

    /**
     * 删除结点(node)，并返回被删除的结点
     */
    private void remove(Node<T> node) {
        Node<T> child, parent;
        boolean color;
        // 被删除节点的"左右孩子都不为空"的情况。
        if ((node.left != null) && (node.right != null)) {
            // 被删节点的后继节点。(称为"取代节点")
            // 用它来取代"被删节点"的位置，然后再将"被删节点"去掉。
            Node<T> replace = node;
            // 获取后继节点
            replace = replace.right;
            while (replace.left != null)
                replace = replace.left;

            // "node节点"不是根节点(只有根节点不存在父节点)
            if (parentOf(node) != null) {
                if (parentOf(node).left == node)
                    parentOf(node).left = replace;
                else
                    parentOf(node).right = replace;
            } else {
                // "node节点"是根节点，更新根节点。
                this.root = replace;
            }

            // child是"取代节点"的右孩子，也是需要"调整的节点"。
            // "取代节点"肯定不存在左孩子！因为它是一个后继节点。
            child = replace.right;
            parent = parentOf(replace);
            // 保存"取代节点"的颜色
            color = colorOf(replace);

            // "被删除节点"是"它的后继节点的父节点"
            if (parent == node) {
                parent = replace;
            } else {
                // child不为空
                if (child != null)
                    setParent(child, parent);
                parent.left = child;

                replace.right = node.right;
                setParent(node.right, replace);
            }

            replace.parent = node.parent;
            replace.color = node.color;
            replace.left = node.left;
            node.left.parent = replace;

            if (color == BLACK)
                removeFixUp(child, parent);

            node = null;
            return;
        }

        if (node.left != null) {
            child = node.left;
        } else {
            child = node.right;
        }

        parent = node.parent;
        // 保存"取代节点"的颜色
        color = node.color;

        if (child != null)
            child.parent = parent;

        // "node节点"不是根节点
        if (parent != null) {
            if (parent.left == node)
                parent.left = child;
            else
                parent.right = child;
        } else {
            this.root = child;
        }

        if (color == BLACK)
            removeFixUp(child, parent);
        node = null;
    }

    /**
     * 删除结点(z)，并返回被删除的结点
     */
    public void remove(T key) {
        Node<T> node;
        if ((node = search(root, key)) != null) {
            remove(node);
        }
    }

    /**
     * 销毁红黑树
     */
    public void clear() {
        destroy(root);
        root = null;
    }

    private void destroy(Node<T> tree) {
        if (tree == null) {
            return;
        }
        if (tree.left != null) {
            destroy(tree.left);
        }
        if (tree.right != null) {
            destroy(tree.right);
        }
        tree = null;
    }

    /**
     * direction  --  0，该节点是根节点;-1，该节点是它的父结点的左孩子;1，表该节点是它的父结点的右孩子。
     */
    public void print() {
        if (root != null)
            print(root, root.key, 0);
    }

    private void print(Node<T> tree, T key, int direction) {
        if (tree != null) {
            if (direction == 0) {    // tree是根节点
                System.out.printf("%2d(B) is root\n", tree.key);
            } else {                // tree是分支节点
                System.out.printf("%2d(%s) is %2d's %6s child\n", tree.key, isRed(tree) ? "R" : "B", key, direction == 1 ? "right" : "left");
            }
            print(tree.left, tree.key, -1);
            print(tree.right, tree.key, 1);
        }
    }


}
