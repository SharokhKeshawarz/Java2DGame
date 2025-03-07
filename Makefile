JAVAC = javac
JAVA = java
JAR = jar
SRC_DIR = src
BIN_DIR = bin
RES_DIR = src/main/resources/assets  # Only copy assets!
JAR_FILE = $(BIN_DIR)/game.jar  # Change to your desired JAR file name

# Find all .java files in the src directory
SOURCES = $(shell find $(SRC_DIR) -name "*.java")

# Convert .java file paths to .class file paths in bin directory
CLASSES = $(SOURCES:$(SRC_DIR)/%.java=$(BIN_DIR)/%.class)

# Default target
all: $(CLASSES) copy-resources create-jar

# Rule to compile .java files into .class files in bin directory
$(BIN_DIR)/%.class: $(SRC_DIR)/%.java
	@mkdir -p $(dir $@)
	$(JAVAC) -d $(BIN_DIR) $<

# Copy ONLY the assets directory into bin/
copy-resources:
	@mkdir -p $(BIN_DIR)/assets
	@cp -r $(RES_DIR) $(BIN_DIR)/

# Create a JAR file containing the compiled classes and resources
create-jar: $(CLASSES)
	@$(JAR) cmf $(BIN_DIR)/META-INF/MANIFEST.MF $(JAR_FILE) -C $(BIN_DIR) .

# Run the JAR file
run-jar:
	@$(JAVA) -jar $(JAR_FILE)

# Run a specified class (alternative to running JAR)
run:
	$(JAVA) -cp $(BIN_DIR) src.main.Main

# Clean compiled files
clean:
	rm -rf $(BIN_DIR)/* $(JAR_FILE)
