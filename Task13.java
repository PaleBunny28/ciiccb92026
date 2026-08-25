package Task13;

// Base Class
class Afritada {
    public void showIngredients() {
        String[] Ingredients = {"Tomato Sauce", "Potatoes & Carrots"};
        System.out.println("Afritada = " + java.util.Arrays.toString(Ingredients) + "🍲 \n");
    }
}

// Extended Class 1 — Mechado
class Mechado extends Afritada {
    public void showIngredients() {
        String[] Ingredients = {
            "Tomato Sauce",
            "Potatoes & Carrots",
            "Tomato Paste"
        };
        System.out.println("Mechado = " + java.util.Arrays.toString(Ingredients) + "🍲 \n");
    }
}

// Extended Class 2 — Menudo
class Menudo extends Afritada {
    public void showIngredients() {
        String[] Ingredients = {
            "Tomato Sauce",
            "Potatoes & Carrots",
            "Liver Spread",
            "Raisins",
            "Hotdog"
        };
        System.out.println("Menudo = " + java.util.Arrays.toString(Ingredients) + "🍲 \n");
    }
}

// Extended Class 3 — Caldereta
class Caldereta extends Afritada {
    public void showIngredients() {
        String[] Ingredients = {
            "Tomato Sauce",
            "Potatoes & Carrots",
            "Tomato Paste",
            "Liver Spread",
            "Raisins",
            "Hotdog",
            "Cheese"
        };
        System.out.println("Caldereta = " + java.util.Arrays.toString(Ingredients) + "🍲 \n");
    }
}

// ✅ ONLY this class is public — MUST match filename exactly
public class Task13 {
    public static void main(String[] args) {
        Afritada meal1 = new Afritada();
        Afritada meal2 = new Mechado();
        Afritada meal3 = new Menudo();
        Afritada meal4 = new Caldereta();

        meal1.showIngredients();
        meal2.showIngredients();
        meal3.showIngredients();
        meal4.showIngredients();
    }
}
