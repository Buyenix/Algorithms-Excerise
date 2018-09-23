public class PlotRunningTimes {
    public static void main(String[] args) {
        //String sortName = args[0];
        int maxX = 100000;
        double maxY = SortCompare.timeRandomInput("Selection", maxX, 1);
        StdDraw.setXscale(-0.5, maxX);
        StdDraw.setYscale(-0.5, maxY * 1.02);
        StdDraw.setPenRadius(0.005);
        for (int j = 0; j < args.length; j++) {
            double prevX = 0.0, prevY = 0.0;
            StdOut.println("Start...");
            for (int i = 0; i < maxX; i += 10000) {
                double time = SortCompare.timeRandomInput(args[j], i, 1);
                StdOut.println("N: " + i + ", time: " + time);
                StdDraw.point(i, time);
                StdDraw.line(prevX, prevY, i, time);
                prevX = (double)i;
                prevY = time;
            }
            StdDraw.setYscale(-0.5, prevY + 100);
        }
    }
}