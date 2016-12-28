package i.dont.care.search;

public class SearchInfo {
	
	private int maxDepth = 0;
	private int minPath = 0;
	private int totalNodes = 0;
	private long elapsedTime = 0;
	
	public SearchInfo(int maxDepth, int minPath, int totalNodes, long elapsedTime) {
		this.maxDepth = maxDepth;
		this.minPath = minPath;
		this.totalNodes = totalNodes;
		this.elapsedTime = elapsedTime;
	}
	
	public SearchInfo() {
	}
	
	public int getMaxDepth() {
		return maxDepth;
	}
	
	public void setMaxDepth(int maxDepth) {
		this.maxDepth = maxDepth;
	}
	
	public int getMinPath() {
		return minPath;
	}
	
	public void setMinPath(int minPath) {
		this.minPath = minPath;
	}
	
	public int getTotalNodes() {
		return totalNodes;
	}
	
	public void setTotalNodes(int totalNodes) {
		this.totalNodes = totalNodes;
	}
	
	public long getElapsedTime() {
		return elapsedTime;
	}
	
	public void setElapsedTime(long elapsedTime) {
		this.elapsedTime = elapsedTime;
	}
	
	public double getExtensiveness() {
		return minPath != 0 ? (double) totalNodes / minPath : 0;
	}
	
	public double getDirectionality() {
		return minPath != 0 ? Math.pow(totalNodes, - 1.0 / minPath) : 0;
	}
	
	public double getViewEfficiency() {
		return totalNodes != 0 ? (double) elapsedTime / totalNodes : 0;
	}
	
	@Override
	public String toString() {
		return String.format("Время работы работы алгоритма T = %d мс.\n" +
						"Глубина поиска D = %d,\n" +
						"длина пути решения L = %d,\n" +
						"общее число порождённых вершин N = %d,\n" +
						"разветвлённость поиска R = %.2f,\n" +
						"направленность поиска W = %.2f,\n" +
						"эффективность просмотра вершин tc = %.2f",
				elapsedTime, maxDepth, minPath, totalNodes, getExtensiveness(),
				getDirectionality(), getViewEfficiency());
	}
}
