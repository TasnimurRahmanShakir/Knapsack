# Knapsack
Here knapsack is like a container or a bag. Suppose we have given some items which have some weights or profits. We have to put some items in the knapsack in such a way total value produces a maximum profit. Also find which Item we choose to find maximum profit.
Algorithm for knapsack:
Input: Weights, Values
Output: P[n, W]
for w = 0 to W do
  P[0, w] = 0
end
for i = 1 to n do
  for w = 1 to W do
    if wi ≤ w then
      P[i, w] = max{v[i] + P[i − 1, w − wi], P[i − 1, w]}
    end
    else
      P[i, w] = P[i − 1, w]
    end
  end
end
