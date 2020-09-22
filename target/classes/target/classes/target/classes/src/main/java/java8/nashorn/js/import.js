// Java provides a facility to import Java package inside the JavaScript code.

print(java.lang.Math.sqrt(4));

var importFile = new JavaImporter(java.util);
var a = new importFile.ArrayList();
a.add(12);
a.add(20);
print(a);
print(a.getClass());