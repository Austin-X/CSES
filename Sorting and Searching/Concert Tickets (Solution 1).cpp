#include <bits/stdc++.h>
using namespace std;

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);
  
  int n, m, x;
  cin >> n >> m;
  int customers[m];
  map<int, int> map;
  for (int i = 0; i < n; i ++) {
    cin >> x;
    if (map.count(x) == 1) map[x] ++;
    else map.emplace(x, 1);
  }
  for (int i = 0; i < m; i ++) cin >> customers[i];


  for (int i = 0; i < m; i ++) {
    auto it = map.upper_bound(customers[i]);
    if (it == map.begin()) cout << "-1\n";
    else {
      it --;
      int val = it->first;
      cout << val << "\n";
      map[val] --;
      if (map[val] == 0) map.erase(val);
    } 
  }

  return 0;
}
