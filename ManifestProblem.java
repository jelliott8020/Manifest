import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ManifestProblem {
    public static void main(String[] args) {
        Solution sol = new Solution();
        
        Package foo = new Package("Foo");
        Package bar = new Package("Bar");
        Package baz = new Package("Baz");
        Package bonk = new Package("Bonk");

        List<Package> fooDep = new ArrayList<>();
        fooDep.add(bar);
        foo.dependencies = fooDep;

        List<Package> barDep = new ArrayList<>();
        barDep.add(baz);
        barDep.add(bonk);
        bar.dependencies = barDep;

        List<Package> bonkDep = new ArrayList<>();
        bonkDep.add(bar);
        bonk.dependencies = bonkDep;

        List<Package> manifest = new ArrayList<>();
        manifest.add(foo);
        manifest.add(bar);
        manifest.add(baz);
        manifest.add(bonk);

        Manifest mf = new Manifest(manifest);

        if (sol.checkForCycleRecursion(mf)) {
            System.out.println("Found cycle");
        } else {
            System.out.println("No cycle");
        }
    }
}

class Solution {

    public boolean checkForCycleRecursion(Manifest manifest) {
        for (Package pkg : manifest.packages) {
            Set<Package> set = new HashSet<>();
            if (dfs(pkg, set)) return true;
        }
        return false;
    }

    /**
     * Depth First Search
     * Time complexity is O(n) where n == # of packages
     * Space complexity is dependent on # of dependencies in each package
     *  which will dictate the # of recursive calls on the stack at one time
     */
    private boolean dfs(Package pkg, Set<Package> set) {
        if (pkg == null || pkg.dependencies == null) return false;
        if (set.contains(pkg)) return true;
        set.add(pkg);

        for (Package dep : pkg.dependencies) {
            if (dfs(dep, set)) return true;
        }

        return false;
    }
}

class Package {
    public String name;
    public List<Package> dependencies;
    public Package(String name, List<Package> dependencies) {
        this.name = name;
        this.dependencies = dependencies;
    }
    public Package(String name) {
        this.name = name;
    }
}

class Manifest {
    public List<Package> packages;
    public Manifest(List<Package> packages) {
        this.packages = packages;
    }
}

