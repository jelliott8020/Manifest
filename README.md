# Manifest

## Problem
Problem: Finding Circular Dependencies
Given a manifest of packages and their dependencies, write a function (or functions) that determines if that manifest contains circular dependencies.
A manifest is a map (or object, hashmap, etc.) whose keys are package names (typically strings) with values being an array (or list, etc.) of package names that that package depends on. If a package has no dependencies, it may not appear in the manifest or may appear with an empty array value.
For example given the following details:
•	Package foo depends on bar
•	Package bar depends on baz and bonk
•	Package baz depends on nothing
•	Package bonk depends on foo
We might model this in JavaScript as an object:
const manifest = {
    foo: ['bar'],
    bar: ['baz', 'bonk'],
    bonk: ['bar'],
};
You’ll note that foo depends on bar, bar depends on bonk, and bonk depends on bar which is a circular dependency. So your function, when called with this manifest, should return true.

## Solution
This is a question of finding a cycle in a directed graph. Used Depth First Search to check for repeated nodes seen.
