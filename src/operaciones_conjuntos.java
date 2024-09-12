import java.util.HashSet;
import java.util.Set;

public class operaciones_conjuntos {
    static Set <String> union(Set a, Set b) {
        Set<String> union = new HashSet<>();
        union.addAll(a);
        union.addAll(b);

        return union;
    }
    static Set<String> intersection(Set a, Set b) {
        Set <String>intersection = new HashSet();
        intersection.addAll(a);
        intersection.retainAll(b);
        return intersection;
    }
    static Set <String> difference(Set a, Set b) {
        Set <String> intersection = intersection(a, b);
        Set <String> difference = a;
        difference.removeAll(intersection);
        return difference;
    }
    static boolean isSubset(Set a, Set b) {
        Set <String> intersection = intersection(a, b);
        return intersection.size() == b.size();
    }
    static <A,B>Set <String> cartesianProduct(Set<A> a, Set<B> b) {
        Set <String> cartesianProduct = new HashSet<>();
        for (A element:a){
            for (B element1:b){
                cartesianProduct.add("("+element+","+element1+")");
            }

        }
        return cartesianProduct;
    }
    public static void main(String[] args) {
        HashSet lista1 = new HashSet();
        lista1.add("1");
        lista1.add("2");
        lista1.add("3");
        lista1.add("a");
        lista1.add("b");
        lista1.add("c");
        HashSet lista2 = new HashSet();
        lista2.add("a");
        lista2.add("b");
        lista2.add("c");
       System.out.println("Ejemplos: ");
        System.out.println("Union: "+union(lista1,lista2));
        System.out.println("lista2 es un subconjunto de lista1: "+isSubset(lista1,lista2));
        System.out.println("Interseccion: "+intersection(lista1,lista2));
        System.out.println("Diferencia: "+difference(lista1,lista2));
        System.out.println("Producto Cartesiano: "+cartesianProduct(lista1,lista2));


    }
}
