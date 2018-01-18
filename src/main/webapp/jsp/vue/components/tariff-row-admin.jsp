<script type="text/x-template" id="tariff-row-admin-template">

    <div class="row zero-margin center-align">
        <div class="col s2">{{title}}</div>
        <div class="col s2">{{description}}</div>
        <div class="col s2">{{number_of_days}}</div>
        <div class="col s1">{{cost}}</div>
        <div class="col s2">{{currency}}</div>
        <div class="col s2"></div>
        <div class="col s2">
            <a v-if="!edit"
               @click="startEdit"
               class="waves-effect waves-light btn">
                {{buttonText}}
            </a>
            <a v-if="edit"
               @click="finishEdit"
               class="waves-effect waves-light btn">
                {{buttonText}}
            </a>
        </div>
    </div>

</script>

<script>
    Vue.component('tariff-row-admin', {
        props: [
            'id', 'title', 'description',
            'number_of_days', 'cost', 'currency',
            'edit_text', 'save_text'
        ],
        data: function () {
            return {
                edit: false,
            }
        },
        template: "#tariff-row-admin-template",
        methods: {
            startEdit() {
                console.log("Start edit");
                this.edit = true;
            },
            finishEdit() {
                console.log("finish edit");
                this.edit = false;
            },
        },

        beforeMount() {

        },
        mounted: function () {

        },

        computed: {
            buttonText() {
                return this.edit ? this.save_text : this.edit_text;
            }
        }
    })
</script>