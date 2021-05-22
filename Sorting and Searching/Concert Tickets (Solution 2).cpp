#include <bits/stdc++.h>
using namespace std;

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);
  
  int n, m, x;
  cin >> n >> m;
  multiset<int> tickets;
  for (int i = 0; i < n; i ++) {
    cin >> x;
    tickets.insert(x);
  }

  for (int i = 0; i < m; i ++) {
    cin >> x;
    auto it = tickets.upper_bound(x);
    if (it == tickets.begin()) cout << "-1\n";
    else {
      cout << *(--it) << endl;
      tickets.erase(it);
    }
  }

  return 0;
}
