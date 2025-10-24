 public static void findRouteWithIntermediateCities(int source, int dest) {
        double minDistance = findDirectDistance(source, dest);
        String bestRoute = cities[source] + " → " + cities[dest];
        boolean foundBetter = false;
