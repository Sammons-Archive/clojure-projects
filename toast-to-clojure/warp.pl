open FILE, ">" , "in.txt" or die "well fuck";
for (my $i = 0; $i < 10000; $i++) {
print FILE $i;
}