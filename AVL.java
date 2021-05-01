import com.sun.source.tree.Tree;

public class AVL {
    class TreeNode {
        int value;
        int height;
        TreeNode left;
        TreeNode right;

        public TreeNode(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
            this.height = 1;
        }
    }

    private int height(TreeNode node) {
        if (node == null)
            return 0;
        return node.height;
    }

    public int getDifference(TreeNode root) {
        if (root == null)
            return 0;
        return height(root.left) - height(root.right);
    }

    public TreeNode constructAVLTree(TreeNode root, int value) {
        if (root == null)
            return new TreeNode(value);
        if (value < root.value)
            root.left = constructAVLTree(root.left, value);
        else
            root.right = constructAVLTree(root.right, value);
        root.height = 1 + Math.max(height(root.left), height(root.right));
        int balance = getDifference(root);
        root = balanceTree(root, balance, value);
        return root;
    }

    public TreeNode rightRotate(TreeNode root) {
        TreeNode leftNode = root.left;
        TreeNode nextRightNode = leftNode.right;
        root.left = nextRightNode;
        leftNode.right = root;
        root.height = Math.max(height(root.left), height(root.right)) + 1;
        leftNode.height = Math.max(height(leftNode.left), height(leftNode.right)) + 1;
        return leftNode;
    }

    public TreeNode leftRotate(TreeNode root) {
        TreeNode rightNode = root.right;
        TreeNode nextLeftNode = rightNode.left;
        root.right = nextLeftNode;
        rightNode.left = root;
        root.height = Math.max(height(root.left), height(root.right)) + 1;
        rightNode.height = Math.max(height(rightNode.left), height(rightNode.right)) + 1;
        return rightNode;
    }

    public TreeNode balanceTree(TreeNode root, int difference, int key) {
        if (difference > 1) {
            return rightRotate(root);
        } else if (difference < -1) {
            return leftRotate(root);
        } else if (difference > 1 && key > root.left.value) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        } else if (difference < -1 && key < root.right.value) {
            root.right = leftRotate(root.right);
            return leftRotate(root);
        }
        return root;
    }
}
