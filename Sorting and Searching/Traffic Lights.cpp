#include <bits/stdc++.h>
using namespace std;

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);
  
  int x, n, p;
  cin >> x >> n;
  set<int> positions;
  multiset<int> distances;
  positions.insert(0); positions.insert(x);
  distances.insert(x);

  while (n--) {
    cin >> p;
    auto it1 = positions.upper_bound(p), it2 = it1;
    it2 --;
    distances.erase(distances.find(*it1 - *it2));
    distances.insert(p - *it2); distances.insert(*it1 - p);
    positions.insert(p);
    cout << *--distances.end() << ' ';
  }

  return 0;
}
